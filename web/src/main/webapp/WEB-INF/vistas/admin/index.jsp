<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<table>
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th scope="col">Fecha de nacimiento</th>
				<th scope="col">Edad</th>
				<th scope="col">OPCIONES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personas}" var="persona" varStatus="estatus">
				<tr>
					<th scope="row">${persona.id}</th>
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

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>