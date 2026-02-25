package com.ipartek.formacion.ejemplos.restaurantespring.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido.Estado;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	@Query("from Pedido p join fetch p.cliente c join fetch p.menus")
	Collection<Pedido> pedidosConMenus();
	
	@Query("from Pedido p join fetch p.cliente c join fetch p.menus where p.estado = :estado")
	Collection<Pedido> pedidosConMenusPorEstado(Estado estado);
}
