package daos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;

import fabrica.Fabrica;
import oop.Persona;
import oop.PersonaDto;

public class DaoPersonaSqlite implements DaoPersona {

	private static final String URL;
	private static final String USER;
	private static final String PASS;

	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));

			URL = props.getProperty("URL");
			USER = props.getProperty("USER");
			PASS = props.getProperty("PASS");

		} catch (Exception e) {
			throw new DaoException("No se ha podido cargar el fabrica.properties", e);
		}
	}

	@Override
	public Collection<Persona> obtenerTodos() {
		return ejecutarSql("SELECT * FROM personas", mapeadorPersona);
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		return ejecutarSql("SELECT * FROM personas WHERE id=?", mapeadorPersona, id).stream().findFirst();
	}

	@Override
	public Persona insertar(Persona persona) {
		ejecutarSql("INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)", persona.getNombre(),
				java.sql.Date.valueOf(persona.getFechaNacimiento()));

		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		ejecutarSql("UPDATE personas SET nombre=?, fecha_nacimiento=? WHERE id=?", persona.getNombre(),
				java.sql.Date.valueOf(persona.getFechaNacimiento()), persona.getId());

		return persona;
	}

	@Override
	public void borrar(Long id) {
		ejecutarSql("DELETE FROM personas WHERE id=?");
	}

	@Override
	public Collection<Persona> buscarPorNombre(String nombre) {
		return ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", mapeadorPersona, nombre);
	}

	@Override
	public Collection<PersonaDto> obtenerPersonaDtos() {
		return ejecutarSql("SELECT * FROM personas", mapeadorPersonaDto);
	}

	private Function<ResultSet, Persona> mapeadorPersona = rs -> {
		try {
			return new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getDate("fecha_nacimiento").toLocalDate());
		} catch (SQLException e) {
			throw new DaoException("Error en el mapeado de persona", e);
		}
	};

	private Function<ResultSet, PersonaDto> mapeadorPersonaDto = rs -> {
		try {
			return new PersonaDto(rs.getLong("id"), rs.getString("nombre"));
		} catch (SQLException e) {
			throw new DaoException("Error en el mapeado de persona", e);
		}
	};

	private <T> Collection<T> ejecutarSql(String sql, Object... valores) {
		return ejecutarSql(sql, null, valores);
	}

	private <T> Collection<T> ejecutarSql(String sql, Function<ResultSet, T> mapeador, Object... valores) {
		var objetos = new ArrayList<T>();

		try (var con = DriverManager.getConnection(URL, USER, PASS); var pst = con.prepareStatement(sql);) {
			for (int i = 0; i < valores.length; i++) {
				pst.setObject(i + 1, valores[i]);
			}

			if (pst.execute()) {
				var rs = pst.getResultSet();
				while (rs.next()) {
					objetos.add(mapeador.apply(rs));
				}

				return objetos;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

}
