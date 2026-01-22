<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form method="post" class="needs-validation" novalidate>
	<input type="hidden" name="id" value="${persona.id}">

	<jl:label-input etiqueta="Nombre" nombre="nombre"
		valor="${persona.nombre}" requerido="requerido"
		error="Debes introducir un nombre" />
	<jl:label-input etiqueta="Fecha de nacimiento" nombre="fecha"
		tipo="date" valor="${persona.fechaNacimiento}" requerido="requerido"
		error="Debes introducir una fecha de
				nacimiento" />

	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<jl:boton etiqueta="Guardar" />
			<jl:boton etiqueta="Restaurar" tipo="reset" color="danger" />
		</div>
	</div>
</form>

<script src="${pageContext.request.contextPath}/js/validacion-bootstrap.js"></script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>