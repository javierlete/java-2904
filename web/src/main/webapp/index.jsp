<%@page import="daos.DaoPersona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		for (var persona : Fabrica.getObjeto("daos.persona", DaoPersona.class).obtenerTodos()) {
		%>
		<li><%=persona.getNombre()%> <a href="detalle.jsp?id=<%=persona.getId()%>">Detalle</a></li>
		<%
		}
		%>
	</ul>

</body>
</html>