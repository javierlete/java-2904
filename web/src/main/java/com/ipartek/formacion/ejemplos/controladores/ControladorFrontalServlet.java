package com.ipartek.formacion.ejemplos.controladores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oop.Persona;

import java.io.IOException;
import java.time.LocalDate;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final String PREFIJO_REDIRECT = "redirect:/";
	private static final long serialVersionUID = 1L;
	private static final DaoPersona DAO_PERSONA = Fabrica.getObjeto("daos.persona", DaoPersona.class);

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		var ruta = switch (request.getPathInfo()) {
		case "/index" -> index();
		case "/login" -> login();
		case "/logout" -> logout();
		case "/admin", "/admin/index" -> adminIndex();
		case "/admin/borrar" -> adminBorrar();
		case "/admin/formulario" -> adminFormulario();
		default -> error();
		};

		if (ruta != null) {
			if (ruta.startsWith(PREFIJO_REDIRECT)) {
				response.sendRedirect(request.getContextPath() + ruta.replace(PREFIJO_REDIRECT, "/cf/"));
			} else {
				request.getRequestDispatcher("/WEB-INF/vistas/" + ruta + ".jsp").forward(request, response);
			}
		}
	}

	private String index() throws ServletException, IOException {
		var personas = DAO_PERSONA.obtenerTodos();

		request.setAttribute("personas", personas);

		return "index";
	}

	private String login() {
		if ("GET".equals(request.getMethod())) {
			return "login";
		}

		var email = request.getParameter("email");
		var password = request.getParameter("password");

		var loginCorrecto = "javier@email.net".equals(email) && "javier".equals(password);

		if (loginCorrecto) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", email);

			return "redirect:/admin/index";
		} else {
			return "login";
		}
	}

	private String logout() {
		request.getSession().invalidate();

		return "redirect:/login";
	}

	private String adminIndex() {
		var personas = DAO_PERSONA.obtenerTodos();

		request.setAttribute("personas", personas);

		return "admin/index";

	}

	private String adminFormulario() {
		var sId = request.getParameter("id");
		var id = sId == null || sId.isBlank() ? null : Long.parseLong(sId);

		if ("GET".equals(request.getMethod())) {

			if (id != null) {
				var persona = DAO_PERSONA.obtenerPorId(id);

				request.setAttribute("persona", persona.orElse(null));
			}

			return "admin/formulario";
		} else {
			var nombre = request.getParameter("nombre");
			var sFecha = request.getParameter("fecha");

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
		var sId = request.getParameter("id");

		var id = Long.parseLong(sId);

		DAO_PERSONA.borrar(id);

		return "redirect:/admin/index";
	}

	private String error() {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
