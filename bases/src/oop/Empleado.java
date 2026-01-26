package oop;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
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

	public void setDni(String dni) {
		if(dni == null || !dni.matches("[\\dXYZ]\\d{7}[A-Z]")) {
			throw new IllegalArgumentException("El DNI es obligatorio y tiene que tener el formato adecuado");
		}
		
		this.dni = dni;
	}

	public abstract BigDecimal getSueldoMensual();

}
