<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personas</title>
</head>
<body>

	<h1>Personas</h1>

	<nav>
		<ul>
			<li><a href="/">Principal</a></li>

			<c:choose>
				<c:when test="${sessionScope.usuario != null}">
					<li><a href="/admin">Admin</a></li>
					<li>${sessionScope.usuario}</li>
					<li><a href="/logout">Cerrar sesión</a></li>
				</c:when>

				<c:otherwise>
					<li><a href="/login">Iniciar sesión</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>