package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import oop.Persona;

public class DaoPersonaCollection implements DaoPersona {
	private Long siguienteId = 1L;
	
	private Collection<Persona> personas = new ArrayList<>();

	@Override
	public Iterable<Persona> obtenerTodos() {
		return personas;
	}

	@Override
	public Optional<Persona> obtenerPorId(Long id) {
		for (Persona p : personas) {
			if (p.getId() == id) {
				return Optional.of(p);
			}
		}

		return Optional.empty();

//		Optional<Persona> persona = Optional.empty();
//		
//		Iterator<Persona> iterator = personas.iterator();
//		
//		while(iterator.hasNext() && persona.isEmpty()) {
//			var p = iterator.next();
//			
//			if(p.getId() == id) {
//				persona = Optional.of(p);
//			}
//		}
//		
//		return persona;	
	}

	@Override
	public Persona insertar(Persona persona) {
		persona.setId(siguienteId++);
		
		personas.add(persona);

		return persona;
	}

	@Override
	public Persona modificar(Persona persona) {
		for (Persona p : personas) {
			if (p.getId() == persona.getId()) {
				personas.remove(p);
				personas.add(persona);
				
				return persona;
			}
		}

		throw new DaoException("No se ha encontrado la persona " + persona);
	}

	@Override
	public void borrar(Long id) {
		for (Persona p : personas) {
			if (p.getId() == id) {
				personas.remove(p);
			}
		}

		throw new DaoException("No se ha encontrado la persona id=" + id);
	}

	@Override
	public Iterable<Persona> buscarPorNombre(String nombre) {
		var resultado = new ArrayList<Persona>();
		
		for (Persona p : personas) {
			if (p.getNombre().contains(nombre)) {
				resultado.add(p);
			}
		}
		
		return resultado;
	}

}
