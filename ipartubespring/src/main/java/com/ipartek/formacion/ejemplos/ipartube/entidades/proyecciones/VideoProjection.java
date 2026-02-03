package com.ipartek.formacion.ejemplos.ipartube.entidades.proyecciones;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

@Projection(types = Video.class)
public interface VideoProjection {
	Long getId();
	String getTitulo();
	String getDescripcion();
	LocalDateTime getFechaHora();
	String getImagenUrl();
	String getVideoUrl();
	
	PropietarioProjection getPropietario();
}
