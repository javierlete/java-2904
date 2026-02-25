package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.MenuDto;
import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.MapeadorService;

@Service
public class MapeadorServiceImpl implements MapeadorService {

	@Override
	public PedidoDto mapear(Pedido pedido) {
		return new PedidoDto(
				pedido.getId(),
				pedido.getEstado(), 
				pedido.getFechaHora(), 
				pedido.getCliente().getNombre(), 
				pedido.getMenus().stream().map(m -> new MenuDto(
						m.getNombre(), 
						m.getPrecio()
						)
					).toList()
				);
	}

}
