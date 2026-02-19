package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ClienteService;

import lombok.RequiredArgsConstructor;

// Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class ClienteServiceImpl implements ClienteService {

	private final PedidoRepository pedidoRepository;

	@Override
	public Pedido hacerPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@Override
	public Pedido hacerPedido(Long idCliente, Long... idMenus) {
		var pedido = Pedido.builder().cliente(Cliente.builder().id(idCliente).build()).build();

		Arrays.stream(idMenus).forEach(id -> pedido.getMenus().add(Menu.builder().id(id).build()));

		return hacerPedido(pedido);
	}

}
