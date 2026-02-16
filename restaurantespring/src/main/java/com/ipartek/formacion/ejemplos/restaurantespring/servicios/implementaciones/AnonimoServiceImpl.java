package com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.ClienteRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.MenuRepository;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AnonimoService;

import lombok.RequiredArgsConstructor;

//Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Service
public class AnonimoServiceImpl implements AnonimoService {
	private final ClienteRepository clienteRepository;
	private final MenuRepository menuRepository;

	@Override
	public Iterable<Menu> consultarMenus() {
		return menuRepository.findAll();
	}

	@Override
	public Optional<Cliente> autenticar(Cliente cliente) {
		Optional<Cliente> clienteEncontrado = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteEncontrado.isEmpty()) {
			return Optional.empty();
		}
		
		if(!clienteEncontrado.get().getPassword().equals(cliente.getPassword())) {
			return Optional.empty();
		}
		
		return clienteEncontrado;
	}

}
