package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.restaurantespring.servicios.implementaciones.AnonimoServiceImpl;

import lombok.RequiredArgsConstructor;

// Lombok (creamos un constructor para todos los final de manera que Spring inyecta los datos)
@RequiredArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {

    private final AnonimoServiceImpl anonimoService;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("menus", anonimoService.consultarMenus());
		
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
}
