package com.ipartek.formacion.ejemplos.restaurantespring.entidades;
//Lombok

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters y setters, equals y hashcode, toString
@AllArgsConstructor // Constructor de todos los parámetros
@NoArgsConstructor // Constructor vacío
@Builder // Patrón Builder

//JPA
@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Cliente cliente;

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@Builder.Default
	private Collection<Menu> menus = new ArrayList<>();

	public BigDecimal getTotal() {
		return menus.stream().map(menu -> menu.getPrecio()).reduce(BigDecimal.ZERO,
				(total, totalParcial) -> total.add(totalParcial));
	}
}
