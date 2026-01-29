package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Collection;

import com.ipartek.formacion.ejemplos.bibliotecas.daos.Dao;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Video;

public interface DaoVideo extends Dao<Video> {
	Collection<Video> obtenerVideosSegunPropietarioId(Long idPropietario);
	
	Collection<Video> obtenerTodosConPropietarios();
}
