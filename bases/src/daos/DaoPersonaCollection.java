package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import oop.Persona;

public class DaoPersonaCollection implements DaoPersona {
	private Long siguienteId = 1L;

	private Collection<Persona> personas = new ArrayList<>();

	@Override
	public Collection<Persona> obtenerTodos() {
		return personas;
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		return personas.stream().filter(p -> p.getId() == id).findFirst();
	}

	@Override
	public Persona insertar(Persona persona) {
		persona.setId(siguienteId++);

		personas.add(persona);

		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		return personas.stream().filter(p -> p.getId() == persona.getId()).findFirst().orElseThrow(() -> {
			throw new DaoException("No se ha encontrado la persona " + persona);
		});
	}

	@Override
	public void borrar(Long id) {
		personas.remove(personas.stream().filter(p -> p.getId() == id).findFirst()
				.orElseThrow(() -> new DaoException("No se ha encontrado la persona id=" + id)));
	}

	@Override
	public Collection<Persona> buscarPorNombre(String nombre) {
		return personas.stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

}
