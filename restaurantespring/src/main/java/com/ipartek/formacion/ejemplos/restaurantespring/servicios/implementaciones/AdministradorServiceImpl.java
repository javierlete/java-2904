package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

//Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class AdministradorServiceImpl implements AdministradorService {
	
	private final PedidoRepository pedidoRepository;
	
	@Override
	public Iterable<Pedido> listadoPedidos() {
		return pedidoRepository.pedidosConMenus(); //findAll();
	}

}
