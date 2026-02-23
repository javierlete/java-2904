package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.MenuRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

//Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class AdministradorServiceImpl implements AdministradorService {

	private final PedidoRepository pedidoRepository;
	private final MenuRepository menuRepository;

	@Override
	public Iterable<PedidoDto> listadoPedidos() {
		return pedidoRepository.pedidosConMenusReducida(); // findAll();
	}

	@Override
	public Iterable<Menu> consultarMenus() {
		return menuRepository.findAll();
	}

	@Override
	public Menu guardar(Menu menu) {
		return menuRepository.save(menu);
	}

	@Override
	public void borrar(Long id) {
		menuRepository.deleteById(id);
	}

}
