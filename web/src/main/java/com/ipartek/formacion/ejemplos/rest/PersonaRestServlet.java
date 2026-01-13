package com.ipartek.formacion.ejemplos.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oop.Persona;

@WebServlet("/api/v1/personas/*")
public class PersonaRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
				@Override
				public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
					return new JsonPrimitive(src.toString()); // ISO-8601
				}
			}).registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
				@Override
				public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
						throws JsonParseException {
					return LocalDate.parse(json.getAsString());
				}
			}).create();

	private static final DaoPersona DAO = Fabrica.getObjeto("daos.persona", DaoPersona.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		var out = response.getWriter();
		
		if ("/busquedas/buscar-por-nombre".equals(request.getPathInfo())) {
			out.append(GSON.toJson(DAO.buscarPorNombre(request.getParameter("nombre"))));
			return;
		}

		Long id = pedirId(request);

		if (id == null) {
			out.append(GSON.toJson(DAO.obtenerTodos()));
		} else {
			Optional<Persona> persona = DAO.obtenerPorId(id);

			if (persona.isPresent()) {
				out.append(GSON.toJson(persona.get()));
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Persona persona = GSON.fromJson(request.getReader(), Persona.class);

		DAO.insertar(persona);

		response.getWriter().append(GSON.toJson(persona));

		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		Long id = pedirId(request);

		Persona persona = GSON.fromJson(request.getReader(), Persona.class);

		if (id != persona.getId()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		DAO.modificar(persona);

		response.getWriter().append(GSON.toJson(persona));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO.borrar(pedirId(request));

		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	private static Long pedirId(HttpServletRequest request) {
		Long id = null;

		String path = request.getPathInfo();

		if (path != null && path.length() > 1) {
			id = Long.parseLong(path.substring(1));
		}
		return id;
	}

}
