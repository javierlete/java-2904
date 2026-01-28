package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.daos.DaoJpa;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

public class DaoUsuarioJpa extends DaoJpa implements DaoUsuario {
	public DaoUsuarioJpa() {
		super("com.ipartek.formacion.ejemplos.ipartube.entidades");
	}

	@Override
	public Collection<Usuario> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from Usuario", Usuario.class).getResultList());
	}

	@Override
	public Optional<Usuario> obtenerPorId(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.find(Usuario.class, id)));
	}

	@Override
	public Usuario insertar(Usuario usuario) {
		return ejecutarJpa(em -> {
			em.persist(usuario);
			return usuario;
		});
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		return ejecutarJpa(em -> {
			em.merge(usuario);
			return usuario;
		});
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(Usuario.class, id));
			return null;
		});
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return ejecutarJpa(em -> {
			try {
				var usuario = em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResult();
				return Optional.of(usuario);
			} catch (Exception e) {
				return Optional.empty();
			}
		});
	}
}
