package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.MenuRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

//Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class AnonimoServiceImpl implements AnonimoService {
	private final MenuRepository menuRepository;

	@Override
	public Iterable<Menu> consultarMenus() {
		return menuRepository.findAll();
	}

}
