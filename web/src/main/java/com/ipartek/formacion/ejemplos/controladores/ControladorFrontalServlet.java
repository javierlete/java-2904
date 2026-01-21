package com.ipartek.formacion.ejemplos.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ipartek.formacion.ejemplos.acciones.AdministradorAcciones;
import com.ipartek.formacion.ejemplos.acciones.PublicoAcciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String COMANDO_SESION = "SESION";
	public static final String COMANDO_SESION_INVALIDAR = "INVALIDAR";
	
	public static record Interaccion(String metodo, Map<String, String[]> entrada, Map<String, Object> modelo, Map<String, Object> sesion) {
		
	}
	
	private static final String PREFIJO_REDIRECT = "redirect:/";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var metodo = request.getMethod();

		var entrada = request.getParameterMap();
		var modelo = new HashMap<String, Object>();
		var sesion = new HashMap<String, Object>();

		var interaccion = new Interaccion(metodo, entrada, modelo, sesion);

		var ruta = switch (request.getPathInfo()) {
		case "/index" -> PublicoAcciones.index(interaccion);
		case "/login" -> PublicoAcciones.login(interaccion);
		case "/logout" -> PublicoAcciones.logout(interaccion);
		case "/admin", "/admin/index" -> AdministradorAcciones.adminIndex(interaccion);
		case "/admin/borrar" -> AdministradorAcciones.adminBorrar(interaccion);
		case "/admin/formulario" -> AdministradorAcciones.adminFormulario(interaccion);
		default -> error(interaccion);
		};

		modelo.entrySet().forEach(par -> request.setAttribute(par.getKey(), par.getValue()));

		var session = request.getSession();

		if (COMANDO_SESION_INVALIDAR.equals(sesion.get(COMANDO_SESION))) {
			session.invalidate();
		} else {
			sesion.entrySet().forEach(par -> session.setAttribute(par.getKey(), par.getValue()));
		}

		if (ruta != null) {
			if (ruta.startsWith(PREFIJO_REDIRECT)) {
				response.sendRedirect(request.getContextPath() + ruta.replace(PREFIJO_REDIRECT, "/cf/"));
			} else {
				request.getRequestDispatcher("/WEB-INF/vistas/" + ruta + ".jsp").forward(request, response);
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private static String error(Interaccion interaccion) {
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
