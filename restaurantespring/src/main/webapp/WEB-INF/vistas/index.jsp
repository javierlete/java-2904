<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurante Spring</title>
</head>
<body>

	<h1>Restaurante Spring</h1>

	<ul>
		<c:forEach items="${menus}" var="m">
			<li>${m.nombre}: ${m.precio}</li>
		</c:forEach>
	</ul>
</body>
</html>