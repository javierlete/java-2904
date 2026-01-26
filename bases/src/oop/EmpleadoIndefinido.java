package oop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmpleadoIndefinido extends Empleado {
	private BigDecimal sueldoAnual;
	private Integer numeroPagas;

	public EmpleadoIndefinido(Long id, String nombre, LocalDate fechaNacimiento, String dni,
			String numeroSeguridadSocial, BigDecimal sueldoAnual, Integer numeroPagas) {
		super(id, nombre, fechaNacimiento, dni, numeroSeguridadSocial);
		this.sueldoAnual = sueldoAnual;
		this.numeroPagas = numeroPagas;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoAnual.divide(new BigDecimal(numeroPagas), 2, RoundingMode.HALF_UP);
	}

}
