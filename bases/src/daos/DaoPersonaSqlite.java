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
		var personas = new ArrayList<Persona>();

		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("SELECT * FROM personas");
				var rs = pst.executeQuery()) {
			while (rs.next()) {
				personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"),
						rs.getDate("fecha_nacimiento").toLocalDate()));
			}

			return personas;
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		Optional<Persona> persona = Optional.empty();

		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("SELECT * FROM personas WHERE id=?");) {

			pst.setLong(1, id);

			var rs = pst.executeQuery();

			if (rs.next()) {
				persona = Optional.of(new Persona(rs.getLong("id"), rs.getString("nombre"),
						rs.getDate("fecha_nacimiento").toLocalDate()));
			}

			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	@Override
	public Persona insertar(Persona persona) {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("INSERT INTO personas (nombre, fecha_nacimiento) VALUES (?,?)");) {

			pst.setString(1, persona.getNombre());
			pst.setDate(2, java.sql.Date.valueOf(persona.getFechaNacimiento()));

			pst.executeUpdate();

			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	@Override
	public Persona modificar(Persona persona) {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("UPDATE personas SET nombre=?, fecha_nacimiento=? WHERE id=?");) {

			pst.setString(1, persona.getNombre());
			pst.setDate(2, java.sql.Date.valueOf(persona.getFechaNacimiento()));
			pst.setLong(3, persona.getId());

			pst.executeUpdate();

			return persona;
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("DELETE FROM personas WHERE id=?");) {

			pst.setLong(1, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}

	@Override
	public Collection<Persona> buscarPorNombre(String nombre) {
		var personas = new ArrayList<Persona>();

		try (var con = DriverManager.getConnection(URL, USER, PASS);
				var pst = con.prepareStatement("SELECT * FROM personas WHERE nombre LIKE ?");
				) {
			pst.setString(1, "%" + nombre + "%");
			
			try (var rs = pst.executeQuery()) {
				while (rs.next()) {
					personas.add(new Persona(rs.getLong("id"), rs.getString("nombre"),
							rs.getDate("fecha_nacimiento").toLocalDate()));
				}

				return personas;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
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

}
