package daos;

import static bibliotecas.jdbc.ConectorSql.ejecutarSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import oop.Persona;
import oop.PersonaDto;

public class DaoPersonaSqlite implements DaoPersona {

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

}
