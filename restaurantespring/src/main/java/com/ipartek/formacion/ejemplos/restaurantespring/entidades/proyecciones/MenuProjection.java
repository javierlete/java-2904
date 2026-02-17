package com.ipartek.formacion.ejemplos.restaurantespring.entidades.proyecciones;

import java.math.BigDecimal;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

@Projection(name = "menu", types = Menu.class)
public interface MenuProjection {
	String getNombre();
	BigDecimal getPrecio();
}