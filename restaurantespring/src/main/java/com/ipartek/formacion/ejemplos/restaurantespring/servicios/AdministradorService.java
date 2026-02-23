package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

public interface AdministradorService {
	Iterable<PedidoDto> listadoPedidos();
	
	Iterable<Menu> consultarMenus();
	
	Menu guardar(Menu menu);
	void borrar(Long id);
}
