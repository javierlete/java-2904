package com.ipartek.formacion.ejemplos.controladores;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición
//		Conversión de los datos
//		Creación de objetos
//		Ejecución de la lógica de negocio
//		Empaquetar objetos para la vista siguiente
//		Pasar a la siguiente vista

		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Recepción de datos de petición
		
		var email = request.getParameter("email");
		var password = request.getParameter("password");
		
//		Conversión de los datos
//		Creación de objetos
//		Ejecución de la lógica de negocio
		
		var loginCorrecto = "javier@email.net".equals(email) && "javier".equals(password);
		
//		Empaquetar objetos para la vista siguiente
//		Pasar a la siguiente vista		
		
		if(loginCorrecto) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", email);
			
			response.sendRedirect("admin/index");
		} else {
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
	}
}
