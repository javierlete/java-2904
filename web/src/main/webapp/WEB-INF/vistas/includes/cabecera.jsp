<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jl" %>
<!DOCTYPE html>
<html class="h-100">
<head>
<base href="${pageContext.request.contextPath}/cf/">
<meta charset="UTF-8">
<title>Personas</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.min.css">
<link rel="icon" href="imgs/person-circle.svg">
</head>
<body class="h-100 d-flex flex-column">
	<nav class="navbar navbar-expand-sm bg-dark" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="index">Personas</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link" href="index">Principal</a></li>

				</ul>
				<ul class="navbar-nav mb-2 mb-sm-0">
					<c:choose>
						<c:when test="${sessionScope.usuario != null}">
							<li class="nav-item"><a class="nav-link" href="admin">Admin</a></li>
							<li class="navbar-text">${sessionScope.usuario}</li>
							<li class="nav-item"><a class="nav-link" href="logout">Cerrar
									sesión</a></li>
						</c:when>

						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="login">Iniciar
									sesión</a></li>
						</c:otherwise>
					</c:choose>
				</ul>

			</div>
		</div>
	</nav>

	<%="<main class='container flex-grow-1 my-5'>"%>