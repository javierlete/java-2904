package com.ipartek.formacion.ejemplos.ipartube.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
