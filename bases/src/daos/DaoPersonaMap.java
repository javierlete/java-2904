package daos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import oop.Persona;
import oop.PersonaDto;

public class DaoPersonaMap implements DaoPersona {
	private Long siguienteId = 1L;
	
	private Map<Long, Persona> personas = new HashMap<>();

	@Override
	public Collection<Persona> obtenerTodos() {
		return personas.values();
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		return Optional.ofNullable(personas.get(id));
	}

	@Override
	public Persona insertar(Persona persona) {
		persona.setId(siguienteId++);
		
		personas.put(persona.getId(), persona);

		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		personas.put(persona.getId(), persona);

		return persona;
	}

	@Override
	public void borrar(Long id) {
		personas.remove(id);
	}

	@Override
	public Collection<Persona> buscarPorNombre(String nombre) {
		return personas.values().stream().filter(p -> p.getNombre().contains(nombre)).toList();
	}

	@Override
	public Collection<PersonaDto> obtenerPersonaDtos() {
		return personas.values().stream().map(p -> new PersonaDto(p.getId(), p.getNombre())).toList();
	}

}
