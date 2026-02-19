package com.ipartek.formacion.ejemplos.restaurantespring.config;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Cliente;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
public class Usuario extends Cliente implements UserDetails {

	private static final long serialVersionUID = 2035699143174255356L;

	public Usuario(Cliente cliente) {
		super(cliente.getId(), cliente.getNombre(), cliente.getApellidos(), cliente.getEmail(), cliente.getPassword());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new GrantedAuthority() {

			@Override
			public @Nullable String getAuthority() {
				return getId() != null && getId() == 1 ? "ROLE_ADMINISTRADOR" : "ROLE_USUARIO";
			}
			
			@Override
			public String toString() {
				return getAuthority();
			}
			
		}
		);
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

}
