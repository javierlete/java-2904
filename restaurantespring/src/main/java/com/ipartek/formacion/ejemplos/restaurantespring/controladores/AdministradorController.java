package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AdministradorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")
public class AdministradorController {
	private final AdministradorService administradorService;
	
	@GetMapping("/pedidos")
	public String pedidos(Model modelo) {
		modelo.addAttribute("pedidos", administradorService.listadoPedidos());
		
		return "administracion/pedidos";
	}
}
