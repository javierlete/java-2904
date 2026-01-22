package com.ipartek.formacion.ejemplos.acciones;

import static com.ipartek.formacion.ejemplos.bibliotecas.controladores.ControladorFrontalServlet.COMANDO_SESION;
import static com.ipartek.formacion.ejemplos.bibliotecas.controladores.ControladorFrontalServlet.COMANDO_SESION_INVALIDAR;

import com.ipartek.formacion.ejemplos.bibliotecas.controladores.ControladorFrontalServlet.Interaccion;
import com.ipartek.formacion.ejemplos.bibliotecas.controladores.Ruta;
import com.ipartek.formacion.ejemplos.negocio.AnonimoNegocio;

import bibliotecas.fabrica.Fabrica;

public class PublicoAcciones {

	public static final AnonimoNegocio ANONIMO_NEGOCIO = Fabrica.getObjeto("negocio.anonimo", AnonimoNegocio.class);

	@Ruta("/index")
	public static String index(Interaccion interaccion) {
		var personas = ANONIMO_NEGOCIO.obtenerPersonas();
	
		interaccion.modelo().put("personas", personas);
	
		return "index";
	}

	@Ruta("/login")
	public static String login(Interaccion interaccion) {
		if ("GET".equals(interaccion.metodo())) {
			return "login";
		}
	
		var email = interaccion.entrada().get("email")[0];
		var password = interaccion.entrada().get("password")[0];
	
		var loginCorrecto = ANONIMO_NEGOCIO.autenticar(email, password);
	
		if (loginCorrecto) {
			interaccion.sesion().put("usuario", email);
	
			return "redirect:/admin/index";
		} else {
			return "login";
		}
	}

	@Ruta("/logout")
	public static String logout(Interaccion interaccion) {
		interaccion.sesion().put(COMANDO_SESION, COMANDO_SESION_INVALIDAR);
	
		return "redirect:/login";
	}

}
