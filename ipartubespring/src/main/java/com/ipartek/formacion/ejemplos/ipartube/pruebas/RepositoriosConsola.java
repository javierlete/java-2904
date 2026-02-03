package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;
import com.ipartek.formacion.ejemplos.ipartube.repositorios.UsuarioRepository;
import com.ipartek.formacion.ejemplos.ipartube.repositorios.VideoRepository;

@Component
public class RepositoriosConsola implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VideoRepository videoRepository;

	@Override
	public void run(String... args) throws Exception {
		var javier = Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").build();

		usuarioRepository.save(javier);

		var pepe = Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").build();
		var juan = Usuario.builder().nombre("Juan").email("juan@email.net").password("juan").build();
		var pedro = Usuario.builder().nombre("Pedro").email("pedro@email.net").password("pedro").build();
		
		usuarioRepository.saveAll(List.of(pepe, juan, pedro));

		for (var usuario : usuarioRepository.findAll()) {
			System.out.println(usuario);
		}

		System.out.println(usuarioRepository.findByEmail("juan@email.net"));

		var video1 = Video.builder().propietario(pedro).titulo("Prueba 1").videoUrl("http://video").build();
		
		videoRepository.save(video1);
	}

}
