package com.ipartek.formacion.ejemplos.ipartube.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;
import com.ipartek.formacion.ejemplos.ipartube.repositorios.VideoRepository;

@RestController
@RequestMapping("/api/v2/videos")
public class VideoRestController {
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	public Iterable<VideoDto> obtenerTodos() {
		return videoRepository.findBy(VideoDto.class);
	}
	
	@GetMapping("{id}")
	public Video obtenerPorId(Long id) {
		var video = videoRepository.findById(id);
		
		if(video.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return video.get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Video insertar(Video video) {
		return videoRepository.save(video);
	}

	@PutMapping("{idVideo}")
	public Video modificar(Long idVideo, Video video) {
		if(video.getId() != idVideo) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return videoRepository.save(video);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrar(Long id) {
		videoRepository.deleteById(id);
	}
	
}
