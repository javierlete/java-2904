package com.ipartek.formacion.ejemplos.ipartube.entidades;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
	private Long id;
	private String titulo;
	private String descripcion;
	private LocalDateTime fechaHora;
	private String imagenUrl;
	private String videoUrl;
	
	private Usuario usuario;
}
