package com.ipartek.formacion.ejemplos.restaurantespring.config;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Usuario;

public class UsuarioLogin extends Usuario implements UserDetails {

	private static final long serialVersionUID = 2035699143174255356L;

	private String rol;

	public UsuarioLogin(Usuario usuario) {
		super(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getPassword());

		rol = "ROLE_" + usuario.getClass().getSimpleName().toUpperCase();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String rol = this.rol == null ? "ROLE_ANONIMO" : this.rol;

		return List.of(new GrantedAuthority() {

			@Override
			public @Nullable String getAuthority() {
				return rol;
			}

			@Override
			public String toString() {
				return getAuthority();
			}

		});

	}

	@Override
	public String getUsername() {
		return getEmail();
	}

}
