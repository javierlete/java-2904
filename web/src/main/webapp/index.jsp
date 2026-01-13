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

String cId = "";
String cNombre = "";
String cFecha = "";

if (id != null && "editar".equals(op)) {
	var persona = dao.obtenerPorId(id).orElse(null);

	if (persona != null) {
		cId = id == null ? "" : String.valueOf(id);
		cNombre = persona.getNombre();
		cFecha = persona.getFechaNacimiento() != null ? persona.getFechaNacimiento().toString() : "";
	}
}

if ("POST".equals(request.getMethod())) {
	var nombre = request.getParameter("nombre");
	var sFecha = request.getParameter("fecha");

	var fecha = sFecha.isBlank() ? null : LocalDate.parse(sFecha);

	var insercion = new Persona(null, nombre, fecha);

	dao.insertar(insercion);
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
		<input name="id" readonly placeholder="Id" value="<%=cId%>"> <input
			name="nombre" required placeholder="Nombre" value="<%=cNombre%>">
		<input name="fecha" type="date" value="<%=cFecha%>">

		<button>Guardar</button>
		<button type="reset">Limpiar</button>
	</form>

	<ul>
		<%
		for (var p : dao.obtenerTodos()) {
		%>
		<li><%=p.getNombre()%> <a
			href="index.jsp?op=editar&id=<%=p.getId()%>">[Editar]</a><a
			href="detalle.jsp?id=<%=p.getId()%>">[Detalle]</a> <a
			href="index.jsp?op=borrar&id=<%=p.getId()%>">[Borrar]</a></li>
		<%
		}
		%>
	</ul>

</body>
</html>