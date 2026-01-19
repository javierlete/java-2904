package com.ipartek.formacion.ejemplos.controladores;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición
//		Conversión de los datos
//		Creación de objetos
//		Ejecución de la lógica de negocio
		
		request.getSession().invalidate();
		
//		Empaquetar objetos para la vista siguiente
//		Pasar a la siguiente vista

		response.sendRedirect("login");
	}

}
