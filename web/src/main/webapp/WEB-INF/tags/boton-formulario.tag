<%@ tag body-content="empty"%>
<%@ attribute name="etiqueta" required="true"%>
<%@ attribute name="tipo" required="false"%>
<%@ attribute name="color" required="false"%>
<div class="row mb-3">
	<div class="offset-sm-2 col-sm">
		<button type="${tipo != null ? tipo : 'submit' }"
			class="btn btn-${color != null ? color : 'primary'}">${etiqueta}</button>
	</div>
</div>