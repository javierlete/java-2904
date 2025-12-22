package daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import oop.Persona;

public class DaoPersonaMap implements DaoPersona {
	private Long siguienteId = 1L;
	
	private Map<Long, Persona> personas = new HashMap<>();

	@Override
	public Iterable<Persona> obtenerTodos() {
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
	public Iterable<Persona> buscarPorNombre(String nombre) {
		var resultados = new ArrayList<Persona>();
		
		for(var persona: personas.values()) {
			if(persona.getNombre().contains(nombre)) {
				resultados.add(persona);
			}
		}
		
		return resultados;
	}

}
