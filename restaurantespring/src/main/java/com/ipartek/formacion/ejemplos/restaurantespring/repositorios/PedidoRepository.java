package com.ipartek.formacion.ejemplos.restaurantespring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
