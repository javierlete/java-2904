package com.ipartek.formacion.ejemplos.acciones;

import static com.ipartek.formacion.ejemplos.controladores.ControladorFrontalServlet.*;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;

public class PublicoAcciones {

	public static final DaoPersona DAO_PERSONA = Fabrica.getObjeto("daos.persona", DaoPersona.class);

	public static String index(Interaccion interaccion) {
		var personas = DAO_PERSONA.obtenerTodos();
	
		interaccion.modelo().put("personas", personas);
	
		return "index";
	}

	public static String login(Interaccion interaccion) {
		if ("GET".equals(interaccion.metodo())) {
			return "login";
		}
	
		var email = interaccion.entrada().get("email")[0];
		var password = interaccion.entrada().get("password")[0];
	
		var loginCorrecto = "javier@email.net".equals(email) && "javier".equals(password);
	
		if (loginCorrecto) {
			interaccion.sesion().put("usuario", email);
	
			return "redirect:/admin/index";
		} else {
			return "login";
		}
	}

	public static String logout(Interaccion interaccion) {
		interaccion.sesion().put(COMANDO_SESION, COMANDO_SESION_INVALIDAR);
	
		return "redirect:/login";
	}

}
