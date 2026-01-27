package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.List;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class UsuarioJpaPruebas {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("com.ipartek.formacion.ejemplos.ipartube.entidades");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		// INSERT
		entityManager.persist(Usuario.builder().nombre("Javier").email("javier@dominio").password("mimi").build());
		entityManager.persist(Usuario.builder().nombre("Pepe").email("pepe@dominio").password("mimi").build());
		entityManager.persist(Usuario.builder().nombre("Juan").email("juan@dominio").password("mimi").build());
		
		listado(entityManager);

		// UPDATE
		entityManager.merge(Usuario.builder().id(2L).nombre("Pepillo").email("pepillo@dominio").password("quillo").build());

		// SELECT WHERE id=?
		System.out.println(entityManager.find(Usuario.class, 2L));
		
		// DELETE
		entityManager.remove(entityManager.find(Usuario.class, 2L));

		listado(entityManager);
		
		transaction.commit();

	}

	private static void listado(EntityManager entityManager) {
		// SELECT
		List<Usuario> usuarios = entityManager.createQuery("from Usuario", Usuario.class).getResultList();
		
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
		}
	}
}
