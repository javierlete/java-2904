package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

public interface MapeadorService {
	PedidoDto mapear(Pedido pedido);
}
