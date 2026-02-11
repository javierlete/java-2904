package com.ipartek.formacion.ejemplos.restaurantespring.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data // Getters y setters, equals y hashcode, toString
@AllArgsConstructor // Constructor de todos los parámetros
@NoArgsConstructor // Constructor vacío
@Builder // Patrón Builder

// JPA
@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String nombre;

	@Size(max = 100)
	private String apellidos;

	@NotBlank
	@Email
	@Size(max = 255)
	@Column(unique = true)
	private String email;

	@NotBlank
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$", message = "la contraseña debe tener al menos una minúscula, una mayúscula, un número y un símbolo")
	private String password;
}
