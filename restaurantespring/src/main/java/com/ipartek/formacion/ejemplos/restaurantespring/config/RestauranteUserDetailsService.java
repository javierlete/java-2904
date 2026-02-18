package com.ipartek.formacion.ejemplos.restaurantespring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.ClienteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

// Autenticación
@Configuration
public class RestauranteUserDetailsService implements UserDetailsService {
	private final ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new Usuario(clienteRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario")));
	}

}
