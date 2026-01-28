package com.ipartek.formacion.ejemplos.ipartube.daos;

import java.util.Optional;

import com.ipartek.formacion.ejemplos.bibliotecas.daos.Dao;
import com.ipartek.formacion.ejemplos.ipartube.entidades.Usuario;

public interface DaoUsuario extends Dao<Usuario> {
	Optional<Usuario> obtenerPorEmail(String email);
}
