package com.ipartek.formacion.ejemplos.ipartube.repositorios;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {
	Collection<Video> findByPropietarioId(Long idPropietario);
}
