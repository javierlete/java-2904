package com.ipartek.formacion.ejemplos.ipartube.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;
import com.ipartek.formacion.ejemplos.ipartube.repositorios.VideoRepository;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRestController {
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping("{id}/videos")
	public Iterable<Video> obtenerVideosPorPropietarioId(@PathVariable("id") Long idPropietario) {
		return videoRepository.findByPropietarioId(idPropietario);
	}
}
