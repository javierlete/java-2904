package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.time.LocalDate;
import java.util.Set;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ValidationPruebas {
	private static final ValidatorFactory VF =  Validation.buildDefaultValidatorFactory();
	
	public static void main(String[] args) {
		Validator validator = VF.getValidator();
		
		var usuario = Usuario.builder().nombre("a").password("adfd").email("asdfasd@asdfadsf").dni("12345678A").fechaNacimiento(LocalDate.of(2026, 1,29)).build();
		
		Set<ConstraintViolation<Usuario>> resultado = validator.validate(usuario);
		
		if(resultado.size() == 0) {
			System.out.println("No hay errores");
			
			return;
		}

		for(ConstraintViolation<Usuario> error: resultado) {
			System.out.printf("%s: %s\n", error.getPropertyPath(), error.getMessage());
		}
	}
}
