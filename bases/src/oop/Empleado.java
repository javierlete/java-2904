package oop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado extends Persona {
	private static final int MAYORIA_DE_EDAD = 18;
	
	protected String dni;
	protected String numeroSeguridadSocial;

	public Empleado(Long id, String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial) {
		super(id, nombre, fechaNacimiento);
		setDni(dni);
		setNumeroSeguridadSocial(numeroSeguridadSocial);
	}

	public Empleado(String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial) {
		this(null, nombre, fechaNacimiento, dni, numeroSeguridadSocial);
	}

	@Override
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if(fechaNacimiento == null || !isMayorDeEdad(fechaNacimiento)) {
			throw new IllegalArgumentException("No se admiten empleados sin fecha de nacimiento o menores de edad");
		}
		
		super.setFechaNacimiento(fechaNacimiento);
	}
	
	public static boolean isMayorDeEdad(LocalDate fechaNacimiento) {
		return getEdad(fechaNacimiento) >= MAYORIA_DE_EDAD;
		// return fechaNacimiento.plusYears(MAYORIA_DE_EDAD).isAfter(LocalDate.now());
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if(dni == null || !dni.matches("[\\dXYZ]\\d{7}[A-Z]")) {
			throw new IllegalArgumentException("El DNI es obligatorio y tiene que tener el formato adecuado");
		}
		
		this.dni = dni;
	}

	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}
	
	public abstract BigDecimal getSueldoMensual();

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
