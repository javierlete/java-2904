package com.ipartek.formacion.ejemplos.servlets;

import java.io.IOException;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		var out = response.getWriter();

		StringBuffer sb = new StringBuffer();
		
		for(var persona: Fabrica.getObjeto("daos.persona", DaoPersona.class).obtenerTodos()) {
			sb.append("<li>");
			sb.append(persona.getNombre());
			sb.append("</li>");
		}
		
		out.append(String.format("""
				<!DOCTYPE html>
				<html>
				<head>
				<meta charset="UTF-8">
				<title>Listado de personas</title>
				</head>
				<body>

				<h1>Listado personas</h1>

				<ul>
					%s
				</ul>

				</body>
				</html>
								""", sb.toString()));
	}
}
