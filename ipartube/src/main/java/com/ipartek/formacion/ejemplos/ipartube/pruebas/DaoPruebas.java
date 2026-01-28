package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

public class DaoPruebas {
	private static final DaoUsuario DAO = Fabrica.getObjeto("dao.usuario", DaoUsuario.class);
	
	public static void main(String[] args) {
		DAO.insertar(Usuario.builder().nombre("Javier").email("javier@dominio").password("mimi").build());
		DAO.insertar(Usuario.builder().nombre("Pepe").email("pepe@dominio").password("mimi").build());
		DAO.insertar(Usuario.builder().nombre("Juan").email("juan@dominio").password("mimi").build());
		
		listado();
		
		DAO.modificar(Usuario.builder().id(2L).nombre("Pepillo").email("pepillo@dominio").password("quillo").build());
		
		System.out.println(DAO.obtenerPorId(2L));
		
		DAO.borrar(2L);
		
		listado();
		
		System.out.println(DAO.obtenerPorEmail("pepe@dominio"));
		System.out.println(DAO.obtenerPorEmail("juan@dominio"));
	}
	
	private static void listado() {
		var usuarios = DAO.obtenerTodos();
		
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
		}
	}
}
