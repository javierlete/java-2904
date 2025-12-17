package oop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class EmpleadoPorHoras extends Empleado {
	private BigDecimal precioHora;
	private Integer numeroHoras;

	public EmpleadoPorHoras(Long id, String nombre, LocalDate fechaNacimiento, String dni, String numeroSeguridadSocial,
			BigDecimal precioHora, Integer numeroHoras) {
		super(id, nombre, fechaNacimiento, dni, numeroSeguridadSocial);
		this.precioHora = precioHora;
		this.numeroHoras = numeroHoras;
	}

	public BigDecimal getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numeroHoras, precioHora);
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
		EmpleadoPorHoras other = (EmpleadoPorHoras) obj;
		return Objects.equals(numeroHoras, other.numeroHoras) && Objects.equals(precioHora, other.precioHora);
	}

	@Override
	public String toString() {
		return String.format(
				"EmpleadoPorHoras [precioHora=%s, numeroHoras=%s, dni=%s, numeroSeguridadSocial=%s, id=%s, nombre=%s, fechaNacimiento=%s]",
				precioHora, numeroHoras, dni, numeroSeguridadSocial, id, nombre, fechaNacimiento);
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return precioHora.multiply(new BigDecimal(numeroHoras));
	}

}
