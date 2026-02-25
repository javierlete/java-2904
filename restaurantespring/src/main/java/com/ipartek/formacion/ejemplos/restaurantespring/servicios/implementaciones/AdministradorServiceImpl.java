package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.MenuRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.PedidoRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AdministradorService;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.MapeadorService;

import lombok.RequiredArgsConstructor;

//Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class AdministradorServiceImpl implements AdministradorService {

	private final PedidoRepository pedidoRepository;
	private final MenuRepository menuRepository;
	
	private final MapeadorService mapeadorService;

	@Override
	public Iterable<PedidoDto> listadoPedidos() {
		var pedidos = pedidoRepository.pedidosConMenus();
		
		return pedidos.stream().map(p -> mapeadorService.mapear(p)).toList();
	}

	@Override
	public Iterable<Menu> consultarMenus() {
		return menuRepository.findAll();
	}

	@Override
	public Optional<Menu> menuPorId(Long idMenu) {
		return menuRepository.findById(idMenu);
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
