<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<form method="post" class="needs-validation" novalidate>
	<input type="hidden" name="id" value="${persona.id}">
	<div class="row mb-3">
		<label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		<div class="col-sm">
			<input required type="text" class="form-control" id="nombre"
				name="nombre" value="${persona.nombre}">
			<div class="invalid-feedback">Debes introducir un nombre</div>
		</div>
	</div>
	<div class="row mb-3">
		<label for="fecha" class="col-sm-2 col-form-label">Fecha de
			nacimiento</label>
		<div class="col-sm">
			<input required type="date" class="form-control" id="fecha"
				name="fecha" value="${persona.fechaNacimiento}">
			<div class="invalid-feedback">Debes introducir una fecha de
				nacimiento</div>
		</div>
	</div>

	<div class="row mb-3">
		<div class="offset-sm-2 col-sm">
			<button type="submit" class="btn btn-primary">Guardar</button>
		</div>
	</div>

</form>

<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault()
        event.stopPropagation()
      }

      form.classList.add('was-validated')
    }, false)
  })
})()
</script>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>