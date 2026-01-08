package com.ipartek.formacion.ejemplos.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oop.Persona;

@WebServlet("/hola")
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); // "text/html", "text/plain"
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		
		PrintWriter out = response.getWriter();
		out.println("Otra vez java-2904 desde Eclipse");
		
		DaoPersona dao = Fabrica.getObjeto("daos.persona", DaoPersona.class);
		
		for(Persona p: dao.obtenerTodos()) {
			out.println(p);
		}
	}

}
