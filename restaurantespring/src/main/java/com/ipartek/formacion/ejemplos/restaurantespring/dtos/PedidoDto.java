package com.ipartek.formacion.ejemplos.restaurantespring.dtos;

import java.math.BigDecimal;

public record PedidoDto(Long id, String cliente, String menu, BigDecimal precio) {

}
