package com.ipartek.formacion.ejemplos.ipartube.dtos;

import java.time.LocalDateTime;

public record VideoDto(Long id, String titulo, String descripcion, LocalDateTime fechaHora, String imagenUrl,
		String videoUrl, UsuarioDto propietario) {

}
