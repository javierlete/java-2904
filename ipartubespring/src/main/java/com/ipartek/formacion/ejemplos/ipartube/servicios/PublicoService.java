package com.ipartek.formacion.ejemplos.ipartube.servicios;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public interface PublicoService {
	public Iterable<Video> obtenerVideos();
	public Video obtenerDetalleVideo(Long id);
	public Video guardarVideo(Video video);
}
