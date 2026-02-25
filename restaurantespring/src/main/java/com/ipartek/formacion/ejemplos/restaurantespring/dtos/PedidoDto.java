package com.ipartek.formacion.ejemplos.restaurantespring.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido.Estado;

public record PedidoDto(Long id, Estado estado, LocalDateTime fechaHora, String cliente, List<MenuDto> menus) {

}
