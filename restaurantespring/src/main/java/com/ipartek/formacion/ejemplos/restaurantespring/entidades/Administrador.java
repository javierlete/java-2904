package com.ipartek.formacion.ejemplos.restaurantespring.entidades;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

// Lombok
@Data // Getters y setters, equals y hashcode, toString
@SuperBuilder // Patrón Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

// JPA
@Entity
public class Administrador extends Usuario {
	
}
