package com.ipartek.formacion.ejemplos.restaurantespring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Usuario;
import com.ipartek.formacion.ejemplos.restaurantespring.repositorios.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

// Autenticación
@Configuration
public class RestauranteUserDetailsService implements UserDetailsService {
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado el usuario"));
		
		System.out.println("USUARIO AUTENTICADO: " + usuario);
		
		UsuarioLogin usuarioLogin = new UsuarioLogin(usuario);
		
		System.out.println("USUARIO LOGIN: " + usuarioLogin);
		
		return usuarioLogin;
	}

}
