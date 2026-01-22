package com.ipartek.formacion.ejemplos.bibliotecas.controladores;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import bibliotecas.fabrica.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String COMANDO_SESION = "SESION";
	public static final String COMANDO_SESION_INVALIDAR = "INVALIDAR";

	public static final Properties PROPS = new Properties();
	
	static {
		try {
			PROPS.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static record Interaccion(String metodo, Map<String, String[]> entrada, Map<String, Object> modelo,
			Map<String, Object> sesion) {

	}

	private static final String PREFIJO_REDIRECT = "redirect:/";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var metodoHttp = request.getMethod();

		var entrada = request.getParameterMap();
		var modelo = new HashMap<String, Object>();
		var sesion = new HashMap<String, Object>();

		var interaccion = new Interaccion(metodoHttp, entrada, modelo, sesion);

		var metodo = buscarMetodoPorValorAnotacion(PROPS.getProperty("acciones"), Ruta.class,
				request.getPathInfo());

		System.out.println(metodo);
		
		String ruta = null;

		if (metodo != null) {
			try {
				ruta = (String) metodo.invoke(null, interaccion);
				
				System.out.println(ruta);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
				return;
			}
		} else {
			error(interaccion);
		}

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

	private static String error(Interaccion interaccion) {
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static Method buscarMetodoPorValorAnotacion(String paquete, Class<? extends Annotation> anotacion,
			String valorBuscado) {

		try {
			String ruta = paquete.replace('.', '/');
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			URL url = loader.getResource(ruta);

			if (url == null) {
				return null;
			}

			File directorio = new File(url.toURI());

			for (File archivo : directorio.listFiles()) {
				if (archivo.getName().endsWith(".class")) {

					String nombreClase = paquete + "." + archivo.getName().replace(".class", "");
					Class<?> clazz = Class.forName(nombreClase);

					for (Method metodo : clazz.getDeclaredMethods()) {

						Annotation a = metodo.getAnnotation(anotacion);
						if (a != null) {
							// Obtener el valor del atributo "valor" de la anotación
							Method mValor = anotacion.getMethod("value");
							String valor = (String) mValor.invoke(a);

							if (valorBuscado.equals(valor)) {
								return metodo; // Encontrado
							}
						}
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null; // No encontrado
	}

}
