package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido.Estado;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.MapeadorService;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ServicioException;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.TrabajadorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class TrabajadorServiceImpl implements TrabajadorService {
	private final PedidoRepository pedidoRepository;
	
	private final MapeadorService mapeadorService;

	@Override
	public Iterable<PedidoDto> pedidosAceptados() {
		var pedidos = pedidoRepository.pedidosConMenusPorEstado(Estado.ACEPTADO);
		
		return pedidos.stream().map(p -> mapeadorService.mapear(p)).toList();
	}

	@Override
	public void servir(Long idPedido) {
		var pedido = pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new ServicioException("No se ha encontrado el pedido con id " + idPedido));

		pedido.setEstado(Estado.SERVIDO);
		
		pedidoRepository.save(pedido);
	}

}
