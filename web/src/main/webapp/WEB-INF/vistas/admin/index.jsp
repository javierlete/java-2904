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
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Fecha de nacimiento</th>
				<th>Edad</th>
				<th>OPCIONES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personas}" var="persona" varStatus="estatus">
				<tr>
					<th>${persona.id}</th>
					<td>${persona.nombre}</td>
					<td>${persona.fechaNacimiento}</td>
					<td>${persona.edad.orElse(null)}</td>
					<td>
						<a href="formulario?id=${persona.id}">Editar</a>
						<a href="borrar?id=${persona.id}">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"></td>
				<td>
					<a href="formulario">Añadir</a>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
