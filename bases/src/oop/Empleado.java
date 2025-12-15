package oop;

import java.time.LocalDate;
import java.util.Objects;

public class Empleado extends Persona {
	private String dni;
	private String numeroSeguridadSocial;

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial) {
		super(id, nombre, fechaNacimiento);
		this.dni = dni;
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public Empleado(String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial) {
		this(null, nombre, fechaNacimiento, dni, numeroSeguridadSocial);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dni, numeroSeguridadSocial);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(numeroSeguridadSocial, other.numeroSeguridadSocial);
	}

	@Override
	public String toString() {
		return String.format("Empleado [dni=%s, numeroSeguridadSocial=%s, id=%s, nombre=%s, fechaNacimiento=%s]", dni,
				numeroSeguridadSocial, id, nombre, fechaNacimiento);
	}

}
