package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

public interface ClienteService {
	Pedido hacerPedido(Pedido pedido) throws ServicioException;
	Pedido hacerPedido(Long idCliente, Long... idMenus) throws ServicioException;
}
