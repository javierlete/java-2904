package com.ipartek.formacion.ejemplos.controladores.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;

@WebServlet("/admin/borrar")
public class BorrarAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición
		
		var sId = request.getParameter("id");
		
//		Conversión de los datos
		
		var id = Long.parseLong(sId);
		
//		Creación de objetos
//		Ejecución de la lógica de negocio
		
		Fabrica.getObjeto("daos.persona", DaoPersona.class).borrar(id);
		
//		Empaquetar objetos para la vista siguiente
//		Pasar a la siguiente vista
		
		response.sendRedirect("index");
	}

}
