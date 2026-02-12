package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

public interface AdministradorService {
	Iterable<Pedido> listadoPedidos();
}
