package com.ipartek.formacion.ejemplos.restaurantespring.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoDto(Long id, LocalDateTime fechaHora, String cliente, String menu, BigDecimal precio) {

}
