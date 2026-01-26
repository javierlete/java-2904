package oop;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Local {
	private Long id;
	private String nombre;
	
	private Persona responsable;

	private ArrayList<Persona> personas = new ArrayList<>();

	public Local(Long id, String nombre, Persona responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
	}

	public Local(String nombre, Persona responsable) {
		this(null, nombre, responsable);
	}

	public void entrar(Persona persona) {
		personas.add(persona);
	}
	
	public void salir(Persona persona) {
		personas.remove(persona);
	}

}
