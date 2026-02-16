package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ClienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;
	
	@PostMapping("pedir")
	public String pedir(Long[] ids, Model modelo) {
		clienteService.hacerPedido(1L, ids);
		
		return "redirect:/admin/pedidos";
	}
}
