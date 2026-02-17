package com.ipartek.formacion.ejemplos.restaurantespring.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto;
import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Pedido;

@RepositoryRestResource(path = "pedidos", collectionResourceRel = "pedidos")
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	@Query("from Pedido p join fetch p.cliente c join fetch p.menus")
	Iterable<Pedido> pedidosConMenus();
	
	@Query("""
			select new com.ipartek.formacion.ejemplos.restaurantespring.dtos.PedidoDto(
				p.id, c.nombre, m.nombre, m.precio
			)
			from Pedido p join p.cliente c join p.menus m
			""")
	Iterable<PedidoDto> pedidosConMenusReducida();	
}
