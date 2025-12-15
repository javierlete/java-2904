package oop;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

public class Persona {
	// CONSTANTES
	private static final String NOMBRE_POR_DEFECTO = "ANÓNIMO";

	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private LocalDate fechaNacimiento;

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
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre debe estar rellenado");
		}

		this.nombre = nombre.trim();
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
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

		return Optional.of(Period.between(fechaNacimiento, LocalDate.now()).getYears());
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaNacimiento, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Persona [id=%s, nombre=%s, fechaNacimiento=%s]", id, nombre, fechaNacimiento);
	}

}
