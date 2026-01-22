package com.ipartek.formacion.ejemplos.filtros;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter({"/admin/*", "/cf/admin/*"})
public class AdminFilter extends HttpFilter {

	private static final long serialVersionUID = 3257576231549003718L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var session = request.getSession();
		
		var usuario = (String)session.getAttribute("usuario");
		
		if(usuario == null) {
			response.sendRedirect(request.getContextPath() + "/cf/login");
			return;
		}
		
		super.doFilter(request, response, chain);
	}

}
