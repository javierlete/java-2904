<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administración</title>
</head>
<body>
	<pre>${persona}</pre>

	<form method="post">
		<input type="hidden" name="id" value="${persona.id}">
		<input placeholder="Nombre" name="nombre" value="${persona.nombre}">
		<input name="fecha" type="date" value="${persona.fechaNacimiento}">
	
		<button>Guardar</button>
	</form>
</body>
</html>
