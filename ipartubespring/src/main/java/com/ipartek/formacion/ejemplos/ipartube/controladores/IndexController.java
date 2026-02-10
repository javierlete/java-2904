package com.ipartek.formacion.ejemplos.ipartube.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;
import com.ipartek.formacion.ejemplos.ipartube.gruposvalidacion.NuevoVideo;
import com.ipartek.formacion.ejemplos.ipartube.servicios.PublicoService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private PublicoService publicoService;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("videos", publicoService.obtenerVideos());

		return "index";
	}

	@GetMapping("detalle/{id}")
	public String detalle(@PathVariable Long id, Model modelo) {
		System.out.println(id);

		modelo.addAttribute("video", publicoService.obtenerDetalleVideo(id));

		return "detalle";
	}

	@GetMapping("anadir-video")
	public String formulario(Video video) {
		return "formulario";
	}

	@PostMapping("anadir-video")
	public String formularioPost(@Validated(NuevoVideo.class) Video video, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formulario";
		}

		video.setPropietario(Usuario.builder().id(1L).build());

		publicoService.guardarVideo(video);

		return "redirect:/";
	}
}
