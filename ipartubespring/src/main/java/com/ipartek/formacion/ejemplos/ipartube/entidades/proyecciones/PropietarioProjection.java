package com.ipartek.formacion.ejemplos.ipartube.entidades.proyecciones;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

@Projection(types = Usuario.class)
public interface PropietarioProjection {
	Long getId();
	String getNombre();
}
