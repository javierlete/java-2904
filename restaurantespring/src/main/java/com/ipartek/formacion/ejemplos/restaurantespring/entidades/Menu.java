package com.ipartek.formacion.ejemplos.restaurantespring.entidades;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data // Getters y setters, equals y hashcode, toString
@AllArgsConstructor // Constructor de todos los parámetros
@NoArgsConstructor // Constructor vacío
@Builder // Patrón Builder

//JPA
@Entity
@Table(name = "menus")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String nombre;
	
	@NotNull
	@Positive
	private BigDecimal precio;
	
	@Lob
	@Size(max = 5000)
	private String descripcion;
}
