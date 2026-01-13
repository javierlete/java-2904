<%@page import="oop.Persona"%>
<%@page import="java.time.LocalDate"%>
<%@page import="daos.DaoPersona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
var dao = Fabrica.getObjeto("daos.persona", DaoPersona.class);

var op = request.getParameter("op");
var sId = request.getParameter("id");

var id = sId != null && !sId.isBlank() ? Long.parseLong(sId) : null;

if (id != null && "borrar".equals(op)) {
	dao.borrar(id);
}

if ("POST".equals(request.getMethod())) {
	var nombre = request.getParameter("nombre");
	var sFecha = request.getParameter("fecha");
	
	var fecha = sFecha.isBlank() ? null: LocalDate.parse(sFecha);
	
	var persona = new Persona(null, nombre, fecha);
	
	dao.insertar(persona);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>
</head>
<body>
	<h1>Listado personas</h1>

	<form method="post">
		<input name="id" readonly placeholder="Id"> <input
			name="nombre" required placeholder="Nombre"> <input name="fecha"
			type="date">

		<button>Guardar</button>
		<button type="reset">Limpiar</button>
	</form>

	<ul>
		<%
		for (var persona : dao.obtenerTodos()) {
		%>
		<li><%=persona.getNombre()%> <a
			href="detalle.jsp?id=<%=persona.getId()%>">[Detalle]</a> <a
			href="index.jsp?op=borrar&id=<%=persona.getId()%>">[Borrar]</a></li>
		<%
		}
		%>
	</ul>

</body>
</html>