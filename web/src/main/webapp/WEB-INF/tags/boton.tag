<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ tag body-content="empty"%>
<%@ attribute name="etiqueta" required="false"%>
<%@ attribute name="tipo" required="false"%>
<%@ attribute name="color" required="false"%>
<%@ attribute name="enlace" required="false"%>
<%@ attribute name="icono" required="false"%>
<c:choose>
	<c:when test="${tipo == 'enlace'}">
		<a class="btn btn-sm btn-${color != null ? color : 'primary'}"
			href="${enlace}"> <c:choose>
				<c:when test="${icono != null}">
					<i class="bi bi-${icono}"></i>
				</c:when>
				<c:otherwise>
						${etiqueta}
					</c:otherwise>
			</c:choose>
		</a>
	</c:when>
	<c:otherwise>
		<button type="${tipo != null ? tipo : 'submit' }"
			class="btn btn-${color != null ? color : 'primary'}">${etiqueta}</button>
	</c:otherwise>
</c:choose>
