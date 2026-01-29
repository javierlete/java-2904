package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import com.ipartek.formacion.ejemplos.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoUsuario;
import com.ipartek.formacion.ejemplos.ipartube.daos.DaoVideo;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public class DaoPruebas {
	private static final DaoUsuario DAO_USUARIO = Fabrica.getObjeto("dao.usuario", DaoUsuario.class);
	private static final DaoVideo DAO_VIDEO = Fabrica.getObjeto("dao.video", DaoVideo.class);

	public static void main(String[] args) {
		DAO_USUARIO.insertar(Usuario.builder().nombre("Javier").email("javier@dominio").password("mimi").build());
		DAO_USUARIO.insertar(Usuario.builder().nombre("Pepe").email("pepe@dominio").password("mimi").build());
		DAO_USUARIO.insertar(Usuario.builder().nombre("Juan").email("juan@dominio").password("mimi").build());

		listadoUsuarios();

		DAO_USUARIO.modificar(
				Usuario.builder().id(2L).nombre("Pepillo").email("pepillo@dominio").password("quillo").build());

		System.out.println(DAO_USUARIO.obtenerPorId(2L));

		DAO_USUARIO.borrar(2L);

		listadoUsuarios();

		System.out.println(DAO_USUARIO.obtenerPorEmail("pepe@dominio"));
		System.out.println(DAO_USUARIO.obtenerPorEmail("juan@dominio"));

		Usuario javier = DAO_USUARIO.obtenerPorEmail("javier@dominio").get();
		Usuario juan = DAO_USUARIO.obtenerPorEmail("juan@dominio").get();

		for (int i = 1; i <= 5; i++) {
			DAO_VIDEO.insertar(
					Video.builder().titulo("Video de Javier " + i).videoUrl("http://alskdjflaksdjf" + i).propietario(javier).build());
		}
		for (int i = 1; i <= 5; i++) {
			DAO_VIDEO.insertar(
					Video.builder().titulo("Video de Juan " + i).videoUrl("http://alskdjflaksdjf" + i).propietario(juan).build());
		}

		System.out.println(DAO_VIDEO.obtenerPorId(1L));

		System.out.println(javier);

		for (Video video : DAO_VIDEO.obtenerVideosSegunPropietarioId(javier.getId())) {
			System.out.println(video);
		}
		
		for(Video video: DAO_VIDEO.obtenerTodos()) {
			System.out.println(video);
		}
		
		for(Video video: DAO_VIDEO.obtenerTodosConPropietarios()) {
			System.out.println(video);
		}
	}

	private static void listadoUsuarios() {
		var usuarios = DAO_USUARIO.obtenerTodos();

		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
}
