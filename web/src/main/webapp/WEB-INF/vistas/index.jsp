<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de personas</title>
</head>
<body>

	<pre>${personas}</pre>

	<ul>
		<%--<c:set var="i" value="1"></c:set>--%>

		<c:forEach items="${personas}" var="persona" varStatus="estatus">
			<li>
				<%--${i}--%> ${estatus.index} ${persona.nombre}
			</li>

			<%--<c:set var="i" value="${i+1}"></c:set>--%>
		</c:forEach>
	</ul>
</body>
</html>
