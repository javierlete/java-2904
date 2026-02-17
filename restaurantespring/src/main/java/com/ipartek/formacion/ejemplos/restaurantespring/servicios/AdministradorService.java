package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;

public interface AdministradorService {
	Iterable<PedidoDto> listadoPedidos();
}
