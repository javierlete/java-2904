package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

public interface AdministradorService {
	Iterable<PedidoDto> listadoPedidos();
	
	Iterable<Menu> consultarMenus();
	Optional<Menu> menuPorId(Long idMenu);

	Menu guardar(Menu menu);
	void borrar(Long id);

}
