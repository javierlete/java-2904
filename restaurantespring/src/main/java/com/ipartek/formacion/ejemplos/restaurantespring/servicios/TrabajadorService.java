package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;

public interface TrabajadorService {
	Iterable<PedidoDto> pedidosAceptados();
	void servir(Long idPedido);
}
