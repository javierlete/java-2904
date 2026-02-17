package com.ipartek.formacion.ejemplos.restaurantespring.entidades.proyecciones;

import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

@Projection(name = "pedido", types = Pedido.class)
public interface PedidoProjection {
	Long getId();
	ClienteProjection getCliente();
	Collection<MenuProjection> getMenus();
}