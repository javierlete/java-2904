package com.ipartek.formacion.ejemplos.controladores.admin;

import java.io.IOException;
import java.time.LocalDate;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oop.Persona;

@WebServlet("/admin/formulario")
public class FormularioAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición

		var sId = request.getParameter("id");

//		Conversión de los datos

		var id = sId == null ? null : Long.parseLong(sId);

//		Creación de objetos
//		Ejecución de la lógica de negocio

		if (id != null) {
			var persona = Fabrica.getObjeto("daos.persona", DaoPersona.class).obtenerPorId(id);

//		Empaquetar objetos para la vista siguiente

			request.setAttribute("persona", persona.orElse(null));
		}

//		Pasar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición

		var sId = request.getParameter("id");
		var nombre = request.getParameter("nombre");
		var sFecha = request.getParameter("fecha");

//		Conversión de los datos

		var id = sId.isBlank() ? null : Long.parseLong(sId);
		var fecha = LocalDate.parse(sFecha);

//		Creación de objetos

		var persona = new Persona(id, nombre, fecha);

//		Ejecución de la lógica de negocio

		if(id == null) {
			Fabrica.getObjeto("daos.persona", DaoPersona.class).insertar(persona);
		} else {
			Fabrica.getObjeto("daos.persona", DaoPersona.class).modificar(persona);
		}

//		Empaquetar objetos para la vista siguiente
//		Pasar a la siguiente vista

		response.sendRedirect("index");
	}
}
