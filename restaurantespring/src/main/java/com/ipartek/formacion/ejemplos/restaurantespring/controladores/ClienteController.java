package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.ejemplos.restaurantespring.config.Usuario;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.ClienteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@GetMapping
	@ResponseBody
	public String cliente(@AuthenticationPrincipal Usuario usuario) {
		return usuario.toString();
	}

	@PostMapping("pedir")
	public String pedir(Long[] ids, Model modelo, @AuthenticationPrincipal Usuario usuario) {
		var pedido = clienteService.hacerPedido(usuario.getId(), ids);

		if (usuario.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRADOR"))) {
			return "redirect:/admin/pedidos";
		} else {
			modelo.addAttribute("pedido", pedido);
			
			return "clientes/pedido";
		}
	}
}
