package daos;

import java.util.Collection;

import oop.Persona;

public interface DaoPersona extends Dao<Persona> {
	Collection<Persona> buscarPorNombre(String nombre);
}
