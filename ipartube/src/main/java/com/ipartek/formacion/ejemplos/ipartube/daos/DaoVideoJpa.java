package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.daos.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public class DaoVideoJpa extends DaoJpa implements DaoVideo {
	public DaoVideoJpa() {
		super("com.ipartek.formacion.ejemplos.ipartube.entidades");
	}

	@Override
	public Collection<Video> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from Video", Video.class).getResultList());
	}

	@Override
	public Optional<Video> obtenerPorId(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.find(Video.class, id)));
	}

	@Override
	public Video insertar(Video video) {
		return ejecutarJpa(em -> {
			em.persist(video);
			return video;
		});
	}

	@Override
	public Video modificar(Video video) {
		return ejecutarJpa(em -> {
			em.merge(video);
			return video;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(Video.class, id));
			return null;
		});
	}

	@Override
	public Collection<Video> obtenerVideosSegunPropietarioId(Long idPropietario) {
		return ejecutarJpa(em -> em.createQuery("from Video v where v.propietario.id = :idPropietario", Video.class)
				.setParameter("idPropietario", idPropietario).getResultList());
	}

	@Override
	public Collection<Video> obtenerTodosConPropietarios() {
		return ejecutarJpa(em -> em.createQuery("from Video v join fetch v.propietario", Video.class).getResultList());
	}
}
