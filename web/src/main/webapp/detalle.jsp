<%@page import="daos.DaoPersona"%>
<%@page import="bibliotecas.fabrica.Fabrica"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Persona</title>
</head>
<body>
	<%
	var id = Long.parseLong(request.getParameter("id"));
	var persona = Fabrica.getObjeto("daos.persona", DaoPersona.class).obtenerPorId(id).get();
	%>

	<h1><%=persona.getNombre()%></h1>

	<dl>
		<dt>Fecha</dt>
		<dd><%=persona.getFechaNacimiento()%></dd>

		<dt>Edad</dt>
		<dd><%=persona.getEdad().get()%></dd>
	</dl>
</body>
</html>