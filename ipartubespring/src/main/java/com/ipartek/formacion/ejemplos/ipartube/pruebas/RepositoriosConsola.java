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

		var video8bbb = Video.builder().propietario(javier).titulo("Concierto Diciembre 2025 8BBB")
				.descripcion("Concierto de la 8 Bit Big Band en directo con sus últimos temas")
				.imagenUrl("https://i.ytimg.com/vi_webp/mzjqdhWxg8M/sddefault.webp")
				.videoUrl("https://www.youtube.com/embed/mzjqdhWxg8M").build();
		
		var videoSmoke = Video.builder().propietario(pepe).titulo("Smoke on the Water")
				.descripcion("Video del 50 aniversario del tema")
				.imagenUrl("https://i.ytimg.com/vi_webp/Q2FzZSBD5LE/sddefault.webp")
				.videoUrl("https://www.youtube.com/embed/Q2FzZSBD5LE").build();

		var videoBruno = Video.builder().propietario(pepe).titulo("Uptown Funk")
				.descripcion("Funk de Bruno Mars")
				.imagenUrl("https://i.ytimg.com/vi_webp/OPf0YbXqDm0/sddefault.webp")
				.videoUrl("https://www.youtube.com/embed/OPf0YbXqDm0").build();
		
		videoRepository.saveAll(List.of(video8bbb, videoSmoke, videoBruno));
		

//		for (int i = 1; i <= 5; i++) {
//			var video = Video.builder().propietario(pedro).titulo("Prueba " + i).imagenUrl("https://picsum.photos/400/300?" + i).videoUrl("http://video" + i).build();
//
//			videoRepository.save(video);
//		}
	}

}
