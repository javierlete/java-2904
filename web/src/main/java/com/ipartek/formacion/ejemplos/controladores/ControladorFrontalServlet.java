package com.ipartek.formacion.ejemplos.controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oop.Persona;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final String COMANDO_SESION_INVALIDAR = "INVALIDAR";
	private static final String COMANDO_SESION = "SESION";
	private static final String PREFIJO_REDIRECT = "redirect:/";
	private static final long serialVersionUID = 1L;
	private static final DaoPersona DAO_PERSONA = Fabrica.getObjeto("daos.persona", DaoPersona.class);

	private String metodo = "GET";

	private Map<String, String[]> entrada = null;
	private Map<String, Object> modelo = null;
	private Map<String, Object> sesion = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		entrada = request.getParameterMap();
		modelo = new HashMap<String, Object>();
		sesion = new HashMap<String, Object>();

		metodo = request.getMethod();

		var ruta = switch (request.getPathInfo()) {
		case "/index" -> index();
		case "/login" -> login();
		case "/logout" -> logout();
		case "/admin", "/admin/index" -> adminIndex();
		case "/admin/borrar" -> adminBorrar();
		case "/admin/formulario" -> adminFormulario();
		default -> error();
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

	private String index() throws ServletException, IOException {
		var personas = DAO_PERSONA.obtenerTodos();

		modelo.put("personas", personas);

		return "index";
	}

	private String login() {
		if ("GET".equals(metodo)) {
			return "login";
		}

		var email = entrada.get("email")[0];
		var password = entrada.get("password")[0];

		var loginCorrecto = "javier@email.net".equals(email) && "javier".equals(password);

		if (loginCorrecto) {
			sesion.put("usuario", email);

			return "redirect:/admin/index";
		} else {
			return "login";
		}
	}

	private String logout() {
		sesion.put(COMANDO_SESION, COMANDO_SESION_INVALIDAR);

		return "redirect:/login";
	}

	private String adminIndex() {
		var personas = DAO_PERSONA.obtenerTodos();

		modelo.put("personas", personas);

		return "admin/index";

	}

	private String adminFormulario() {
		var sId = entrada.get("id") == null ? null : entrada.get("id")[0];
		var id = sId == null || sId.isBlank() ? null : Long.parseLong(sId);

		if ("GET".equals(metodo)) {

			if (id != null) {
				var persona = DAO_PERSONA.obtenerPorId(id);

				modelo.put("persona", persona.orElse(null));
			}

			return "admin/formulario";
		} else {
			var nombre = entrada.get("nombre")[0];
			var sFecha = entrada.get("fecha")[0];

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

	private String adminBorrar() {
		var sId = entrada.get("id")[0];

		var id = Long.parseLong(sId);

		DAO_PERSONA.borrar(id);

		return "redirect:/admin/index";
	}

	private String error() {
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
