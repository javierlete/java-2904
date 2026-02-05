package com.ipartek.formacion.ejemplos.ipartube.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.ipartube.repositorios.UsuarioRepository;

@Controller
@RequestMapping("/ejemplo")
public class EjemploController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public String index(Model modelo, String nombre) {
		modelo.addAttribute("nombre", nombre);
		modelo.addAttribute("usuarios", usuarioRepository.findAll());
		
		return "ejemplo";
	}
}
