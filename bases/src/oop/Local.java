package oop;

import java.util.ArrayList;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Persona getResponsable() {
		return responsable;
	}

	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	
	public void entrar(Persona persona) {
		personas.add(persona);
	}
	
	public void salir(Persona persona) {
		personas.remove(persona);
	}

	@Override
	public String toString() {
		return String.format("Local [id=%s, nombre=%s, responsable=%s]", id, nombre, responsable);
	}

}
