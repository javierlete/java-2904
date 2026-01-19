<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<div class="table-responsive">
	<table class="table table-bordered table-striped table-hover">
		<thead class="table-secondary">
			<tr>
				<th class="text-end" scope="col">Id</th>
				<th scope="col">Nombre</th>
				<th class="text-center" scope="col">Fecha de nacimiento</th>
				<th class="text-end" scope="col">Edad</th>
				<th scope="col">OPCIONES</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${personas}" var="persona" varStatus="estatus">
				<tr>
					<th class="text-end" scope="row">${persona.id}</th>
					<td>${persona.nombre}</td>
					<td class="text-center">${persona.fechaNacimiento}</td>
					<td class="text-end">${persona.edad.orElse(null)}</td>
					<td><a class="btn btn-sm btn-primary"
						href="admin/formulario?id=${persona.id}"><i
							class="bi bi-pencil-fill"></i></a> <a class="btn btn-sm btn-danger"
						href="admin/borrar?id=${persona.id}"><i
							class="bi bi-trash-fill"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot class="table-secondary">
			<tr>
				<td colspan="4"></td>
				<td><a class="btn btn-sm btn-primary" href="admin/formulario"><i
						class="bi bi-plus-lg"></i></a></td>
			</tr>
		</tfoot>
	</table>
</div>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>