package com.ipartek.formacion.ejemplos.controladores.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;

@WebServlet("/admin/index")
public class IndexAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición
//		Conversión de los datos
//		Creación de objetos
//		Ejecución de la lógica de negocio
		
		var personas = Fabrica.getObjeto("daos.persona", DaoPersona.class).obtenerTodos();
		
//		Empaquetar objetos para la vista siguiente
		
		request.setAttribute("personas", personas);
		
//		Pasar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/admin/index.jsp").forward(request, response);
	}

}
