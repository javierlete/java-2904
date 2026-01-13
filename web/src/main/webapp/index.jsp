<%@page import="daos.DaoPersona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
var dao = Fabrica.getObjeto("daos.persona", DaoPersona.class);

var op = request.getParameter("op");
var sId = request.getParameter("id");

var id = sId != null ? Long.parseLong(sId) : null;

if (id != null && "borrar".equals(op)) {
	dao.borrar(id);
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