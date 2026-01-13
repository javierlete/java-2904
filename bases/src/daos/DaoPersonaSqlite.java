package daos;

import static bibliotecas.jdbc.ConectorSql.ejecutarSql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import bibliotecas.jdbc.DaoConectorSql;
import bibliotecas.jdbc.DaoException;
import oop.Persona;
import oop.PersonaDto;

public class DaoPersonaSqlite extends DaoConectorSql<Persona> implements DaoPersona {

	public DaoPersonaSqlite() {
		super("personas", "nombre", "fecha_nacimiento");

		mapeador = mapeadorPersona;
		mapeadorInverso = mapeadorPersonaInverso;
	}

	@Override
	public Collection<Persona> buscarPorNombre(String nombre) {
		return ejecutarSql("SELECT * FROM personas WHERE nombre LIKE ?", mapeadorPersona, "%" + nombre + "%");
	}

	@Override
	public Collection<PersonaDto> obtenerPersonaDtos() {
		return ejecutarSql("SELECT * FROM personas", mapeadorPersonaDto);
	}

	private Function<ResultSet, Persona> mapeadorPersona = rs -> {
		try {
			var fecha = rs.getDate("fecha_nacimiento");
			return new Persona(rs.getLong("id"), rs.getString("nombre"), fecha != null ? fecha.toLocalDate() : null);
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

	private Function<Persona, Object[]> mapeadorPersonaInverso = p -> {
		var lista = new ArrayList<Object>();

		lista.add(p.getNombre());

		var fecha = p.getFechaNacimiento();
		lista.add(fecha != null ? java.sql.Date.valueOf(fecha) : null);

		if (p.getId() != null) {
			lista.add(p.getId());
		}

		return lista.toArray();
	};

}
