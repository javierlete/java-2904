package com.ipartek.formacion.ejemplos.ipartube.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public class VideoPruebas {
	public static void main(String[] args) {
		var video = new Video();
		
		video.setTitulo("Prueba");
		
		System.out.println(video.getTitulo());
		System.out.println(video.toString());
		
		var video1 = new Video();
		var video2 = new Video();
		
		System.out.println(video1.equals(video2));
		
		System.out.println(video1.hashCode());
		System.out.println(video2.hashCode());
		
		var video3 = new Video(1L, "Título 1", "Descripción 1", LocalDateTime.now(), "https://urlimagen", "https://urlvideo", Usuario.builder().nombre("Javier").build());
		
		System.out.println(video3);
		
		var video4 = Video.builder().titulo("Título 4").propietario(Usuario.builder().nombre("Pepe").build()).videoUrl("https://videoURL").build();
		
		System.out.println(video4);
	}
}
