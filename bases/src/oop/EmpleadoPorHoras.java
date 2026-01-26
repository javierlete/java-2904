package oop;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmpleadoPorHoras extends Empleado {
	private BigDecimal precioHora;
	private Integer numeroHoras;

	public EmpleadoPorHoras(Long id, String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial,
			BigDecimal precioHora, Integer numeroHoras) {
		super(id, nombre, fechaNacimiento, dni, numeroSeguridadSocial);
		this.precioHora = precioHora;
		this.numeroHoras = numeroHoras;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return precioHora.multiply(new BigDecimal(numeroHoras));
	}

}
