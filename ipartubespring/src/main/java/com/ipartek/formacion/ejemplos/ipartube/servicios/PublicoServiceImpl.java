package com.ipartek.formacion.ejemplos.ipartube.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;
import com.ipartek.formacion.ejemplos.ipartube.repositorios.VideoRepository;

import lombok.extern.java.Log;

@Log
@Service
public class PublicoServiceImpl implements PublicoService {
	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public Iterable<Video> obtenerVideos() {
		log.info("Se han pedido los videos");
		
		return videoRepository.findAll();
	}

	@Override
	public Video obtenerDetalleVideo(Long id) {
		return videoRepository.findById(id).get();
	}

	@Override
	public Video guardarVideo(Video video) {
		return videoRepository.save(video);
	}

}
