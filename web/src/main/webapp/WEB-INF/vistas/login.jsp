<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form method="post">
	<jl:label-input nombre="email" etiqueta="Email" />
	<jl:label-input nombre="password" tipo="password" etiqueta="Contraseña" />

	<jl:boton-formulario etiqueta="Iniciar sesión" />
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>