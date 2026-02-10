package com.ipartek.formacion.ejemplos.ipartube.entidades;

import java.time.LocalDateTime;

import com.ipartek.formacion.ejemplos.ipartube.gruposvalidacion.NuevoVideo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(groups = {NuevoVideo.class})
	@Size(max = 255, groups = {NuevoVideo.class})
	private String titulo;
	
	@Lob
	@Size(max = 2000, groups = {NuevoVideo.class})
	private String descripcion;
	
	@PastOrPresent(groups = {NuevoVideo.class})
	@Builder.Default
	private LocalDateTime fechaHora = LocalDateTime.now();
	
	@Size(max = 255, groups = {NuevoVideo.class})
	private String imagenUrl;
	
	@NotBlank(groups = {NuevoVideo.class})
	@Size(max = 255, groups = {NuevoVideo.class})
	private String videoUrl;
	
	@NotNull
	@ManyToOne
	private Usuario propietario;
}
