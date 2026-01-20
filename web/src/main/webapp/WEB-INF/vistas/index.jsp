<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6 g-4">
	<c:forEach items="${personas}" var="persona" varStatus="estatus">
		<div class="col">
			<div class="card h-100">
				<img src="https://picsum.photos/400/300?${persona.id}" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">${persona.nombre}</h5>
					<p class="card-text">${persona.fechaNacimiento}</p>
				</div>
				<div class="card-footer">
					<small class="text-body-secondary">${estatus.index}</small>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>