package com.ipartek.formacion.ejemplos.acciones;

import static com.ipartek.formacion.ejemplos.bibliotecas.controladores.ControladorFrontalServlet.*;

import java.time.LocalDate;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import oop.Persona;

public class AdministradorAcciones {

	public static final DaoPersona DAO_PERSONA = Fabrica.getObjeto("daos.persona", DaoPersona.class);

	@Ruta("/admin/index")
	public static String adminIndex(Interaccion interaccion) {
		var personas = DAO_PERSONA.obtenerTodos();
	
		interaccion.modelo().put("personas", personas);
	
		return "admin/index";
	
	}

	@Ruta("/admin/formulario")
	public static String adminFormulario(Interaccion interaccion) {
		var sId = interaccion.entrada().get("id") == null ? null : interaccion.entrada().get("id")[0];
		var id = sId == null || sId.isBlank() ? null : Long.parseLong(sId);
	
		if ("GET".equals(interaccion.metodo())) {
	
			if (id != null) {
				var persona = DAO_PERSONA.obtenerPorId(id);
	
				interaccion.modelo().put("persona", persona.orElse(null));
			}
	
			return "admin/formulario";
		} else {
			var nombre = interaccion.entrada().get("nombre")[0];
			var sFecha = interaccion.entrada().get("fecha")[0];
	
			var fecha = LocalDate.parse(sFecha);
	
			var persona = new Persona(id, nombre, fecha);
	
			if (id == null) {
				DAO_PERSONA.insertar(persona);
			} else {
				DAO_PERSONA.modificar(persona);
			}
	
			return "redirect:/admin/index";
		}
	}

	@Ruta("/admin/borrar")
	public static String adminBorrar(Interaccion interaccion) {
		var sId = interaccion.entrada().get("id")[0];
	
		var id = Long.parseLong(sId);
	
		DAO_PERSONA.borrar(id);
	
		return "redirect:/admin/index";
	}

}
