package com.ipartek.formacion.ejemplos.restaurantespring.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.ejemplos.restaurantespring.entidades.Menu;

@RepositoryRestResource(path = "menus", collectionResourceRel = "menus")
public interface MenuRepository extends CrudRepository<Menu, Long> {

}
