package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ClienteService;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ServicioException;

import lombok.RequiredArgsConstructor;

// Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class ClienteServiceImpl implements ClienteService {

	private final PedidoRepository pedidoRepository;

	@Override
	public Pedido hacerPedido(Pedido pedido) throws ServicioException {
		var pedidoCreado = pedidoRepository.save(pedido);

		return pedidoRepository.findById(pedidoCreado.getId()).orElseThrow(() -> new ServicioException(
				"No se ha encontrado el pedido que se acaba de crear con el id " + pedido.getId()));
	}

	@Override
	public Pedido hacerPedido(Long idCliente, Long... idMenus) throws ServicioException {
		// @formatter:off
		var pedido = Pedido.builder()
			.fechaHora(LocalDateTime.now())
			.cliente(Cliente.builder()
				.id(idCliente)
			.build())
		.build();
		// @formatter:on

		Arrays.stream(idMenus).forEach(id -> pedido.getMenus().add(Menu.builder().id(id).build()));

		return hacerPedido(pedido);
	}

}
