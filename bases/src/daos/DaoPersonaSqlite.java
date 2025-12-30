package daos;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

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
		return ejecutarSql("SELECT * FROM personas");
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		return ejecutarSql("SELECT * FROM personas WHERE id=?", id).stream().findFirst();
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
		return ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", nombre);
	}

	@Override
	public Collection<PersonaDto> obtenerPersonaDtos() {
		var personas = new ArrayList<PersonaDto>();

		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("SELECT * FROM personas");
				var rs = pst.executeQuery()) {
			while (rs.next()) {
				personas.add(new PersonaDto(rs.getLong("id"), rs.getString("nombre")));
			}

			return personas;
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	private Collection<Persona> ejecutarSql(String sql, Object... valores) {
		var personas = new ArrayList<Persona>();

		try (var con = DriverManager.getConnection(URL, USER, PASS); var pst = con.prepareStatement(sql);) {
			for (int i = 0; i < valores.length; i++) {
				pst.setObject(i + 1, valores[i]);
			}

			if (pst.execute()) {
				var rs = pst.getResultSet();
				while (rs.next()) {
					personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"),
							rs.getDate("fecha_nacimiento").toLocalDate()));
				}

				return personas;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

}
