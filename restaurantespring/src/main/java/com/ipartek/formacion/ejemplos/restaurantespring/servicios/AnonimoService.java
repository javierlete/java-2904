package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

public interface AnonimoService {
	Iterable<Menu> consultarMenus();
}
