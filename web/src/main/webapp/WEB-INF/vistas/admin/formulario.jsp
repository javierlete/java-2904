<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<form method="post">
		<input type="hidden" name="id" value="${persona.id}">
		<input placeholder="Nombre" name="nombre" value="${persona.nombre}">
		<input name="fecha" type="date" value="${persona.fechaNacimiento}">
	
		<button>Guardar</button>
	</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>