<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp" %>

	<ul>
		<%--<c:set var="i" value="1"></c:set>--%>

		<c:forEach items="${personas}" var="persona" varStatus="estatus">
			<li>
				<%--${i}--%> ${estatus.index} ${persona.nombre}
			</li>

			<%--<c:set var="i" value="${i+1}"></c:set>--%>
		</c:forEach>
	</ul>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp" %>