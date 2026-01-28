package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoUsuarioJpa implements DaoUsuario {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.entidades");

	@Override
	public Collection<Usuario> obtenerTodos() {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		var usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
		
		t.commit();

		return usuarios;
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		var usuario = em.find(Usuario.class, id);
		
		t.commit();

		return Optional.ofNullable(usuario);
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		em.persist(usuario);
		
		t.commit();

		return usuario;
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		em.merge(usuario);
		
		t.commit();

		return usuario;
	}

	@Override
	public void borrar(Long id) {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		em.remove(em.find(Usuario.class, id));
		
		t.commit();
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		var em = EMF.createEntityManager();
		var t = em.getTransaction();

		t.begin();

		Usuario usuario;
		
		try {
			usuario = em.createQuery("from Usuario u where u.email = :email", Usuario.class).setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return Optional.empty();
		}
		
		t.commit();

		return Optional.of(usuario);
	}

}
