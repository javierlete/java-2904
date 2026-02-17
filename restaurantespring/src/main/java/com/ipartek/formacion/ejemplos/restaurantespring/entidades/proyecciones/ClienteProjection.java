package com.ipartek.formacion.ejemplos.restaurantespring.entidades.proyecciones;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;

@Projection(name = "cliente", types = Cliente.class)
public interface ClienteProjection {
	String getNombre();
}
