package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.restaurantespring.servicios.TrabajadorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/trabajador")
public class TrabajadorController {
	private final TrabajadorService trabajadorService;
	
	@GetMapping("pedidos")
	public String pedidos(Model modelo) {
		modelo.addAttribute("pedidos", trabajadorService.pedidosAceptados());
		return "trabajador/pedidos";
	}
	
	@GetMapping("pedidos/servir")
	public String servirPedido(Long id) {
		trabajadorService.servir(id);
		
		return "redirect:/trabajador/pedidos";
	}
}
