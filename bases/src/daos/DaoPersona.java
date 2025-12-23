package daos;

import java.util.Collection;

import oop.Persona;
import oop.PersonaDto;

public interface DaoPersona extends Dao<Persona> {
	Collection<Persona> buscarPorNombre(String nombre);
	
	Collection<PersonaDto> obtenerPersonaDtos();
}
