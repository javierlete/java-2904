package com.ipartek.formacion.ejemplos.restaurantespring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;
import com.ipartek.formacion.ejemplos.restaurantespring.servicios.AdministradorService;

import jakarta.validation.Valid;
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
	
	@GetMapping("/menus")
	public String menus(Model modelo) {
		modelo.addAttribute("menus", administradorService.consultarMenus());
		
		return "administracion/menus";
	}
	
	@GetMapping("/menus/formulario")
	public String menusFormulario(@RequestParam(name = "id", required = false) Long idMenu, Model modelo) {
		if(idMenu != null) {
			modelo.addAttribute("menu", administradorService.menuPorId(idMenu).orElse(null));
		} else {
			modelo.addAttribute("menu", new Menu());
		}
		
		return "administracion/menusformulario";
	}

	@PostMapping("/menus/guardar")
	public String menusGuardar(@Valid Menu menu, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "administracion/menusformulario";
		}
		
		administradorService.guardar(menu);
		
		return "redirect:/admin/menus";
	}
	
	@GetMapping("/menus/borrar")
	public String menuBorrar(Long id ) {
		administradorService.borrar(id);
		
		return "redirect:/admin/menus";
	}
}
