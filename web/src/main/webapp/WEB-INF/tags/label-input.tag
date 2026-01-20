<%@ tag body-content="empty"%>
<%@ attribute name="etiqueta" required="false"%>
<%@ attribute name="tipo" required="false"%>
<%@ attribute name="nombre" required="true"%>
<%@ attribute name="valor" required="false"%>
<%@ attribute name="error" required="false"%>
<%@ attribute name="requerido" required="false"%>
<div class="row mb-3">
	<label for="${nombre}" class="col-sm-2 col-form-label">${etiqueta}</label>
	<div class="col-sm">
		<input ${requerido != null  ? "required" : "" } type="${tipo}"
			class="form-control" id="${nombre}" name="${nombre}" value="${valor}">
		<div class="invalid-feedback">${error}</div>
	</div>
</div>