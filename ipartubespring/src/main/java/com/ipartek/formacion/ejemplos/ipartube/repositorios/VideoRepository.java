package com.ipartek.formacion.ejemplos.ipartube.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

@RepositoryRestResource(collectionResourceRel = "videos", path = "videos")
public interface VideoRepository extends CrudRepository<Video, Long> {
	Collection<Video> findByPropietarioId(Long idPropietario);

	@Query("""
			SELECT new com.ipartek.formacion.ejemplos.ipartube.dtos.VideoDto(
				v.id, v.titulo, v.descripcion, v.fechaHora, v.imagenUrl, v.videoUrl, 
					new com.ipartek.formacion.ejemplos.ipartube.dtos.UsuarioDto(p.id, p.nombre)
			) 
			FROM Video v LEFT JOIN v.propietario p
			""")
	<T> Collection<T> findBy(Class<T> tipo);
}
