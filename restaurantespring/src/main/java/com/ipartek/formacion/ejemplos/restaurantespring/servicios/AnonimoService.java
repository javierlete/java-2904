package com.ipartek.formacion.ejemplos.restaurantespring.servicios;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

public interface AnonimoService {
	Iterable<Menu> consultarMenus();
	Optional<Cliente> autenticar(Cliente cliente);
}
