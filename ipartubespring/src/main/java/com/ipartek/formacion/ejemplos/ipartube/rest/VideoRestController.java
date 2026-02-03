package com.ipartek.formacion.ejemplos.ipartube.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto;
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
}
