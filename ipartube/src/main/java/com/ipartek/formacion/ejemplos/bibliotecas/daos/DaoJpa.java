package com.ipartek.formacion.ejemplos.bibliotecas.daos;

import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoJpa {
	public final EntityManagerFactory emf;

	public DaoJpa(String persistenceUnit) {
		this.emf = Persistence.createEntityManagerFactory(persistenceUnit);
	}
	
	public <T> T ejecutarJpa(Function<EntityManager, T> accion) {
		try (var em = emf.createEntityManager()) {
			var t = em.getTransaction();

			T resultado = null;
			
			try {
				t.begin();

				resultado = accion.apply(em);

				t.commit();
			} catch (Exception e) {
				t.rollback();
			}

			return resultado;
		} catch (Exception e) {
			throw new DaoException("Error en JPA", e);
		}
	}
}
