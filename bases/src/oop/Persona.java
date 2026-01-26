package oop;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import lombok.Data;

@Data
public class Persona {
	// CONSTANTES
	private static final String NOMBRE_POR_DEFECTO = "ANÓNIMO";

	// VARIABLES DE INSTANCIA
	protected Long id;
	protected String nombre;
	protected LocalDate fechaNacimiento;

	// CONSTRUCTORES
	public Persona(Long id, String nombre, LocalDate fechaNacimiento) {
		setId(id);
		setNombre(nombre);
		setFechaNacimiento(fechaNacimiento);
	}

	public Persona(String nombre, LocalDate fechaNacimiento) {
		this(null, nombre, fechaNacimiento);
	}

	public Persona(String nombre) {
		this(null, nombre, null);
	}

	public Persona() {
		this(null, NOMBRE_POR_DEFECTO, null);
	}

	// Constructor de copia
	public Persona(Persona persona) {
		this(persona.getId(), persona.getNombre(), persona.getFechaNacimiento());
	}

	// GETTERS Y SETTERS

	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre debe estar rellenado");
		}

		this.nombre = nombre.trim();
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento != null && fechaNacimiento.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("No se admiten fechas futuras");
		}

		this.fechaNacimiento = fechaNacimiento;
	}

	// MÉTODOS DE INSTANCIA
	public Optional<Integer> getEdad() {
		if (fechaNacimiento == null) {
			return Optional.empty();
		}

		return Optional.of(getEdad(fechaNacimiento));
	}
	
	// MÉTODOS ESTÁTICOS
	public static int getEdad(LocalDate fecha) {
		return Period.between(fecha, LocalDate.now()).getYears();
	}

}
