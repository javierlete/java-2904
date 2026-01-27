package com.ipartek.formacion.ejemplos.ipartube.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
	private Long id;
	private String nombre;
	private String email;
	private String password;
	private LocalDate fechaNacimiento;
	
	public Optional<Integer> getEdad() {
		if(fechaNacimiento == null) {
			return Optional.empty();
		}
		
		return Optional.of(Period.between(fechaNacimiento, LocalDate.now()).getYears());
	}
}
