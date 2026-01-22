package com.ipartek.formacion.ejemplos.negocio;

import java.util.logging.Logger;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import oop.Persona;

public class AnonimoNegocioImpl implements AnonimoNegocio {

	public static final DaoPersona DAO_PERSONA = Fabrica.getObjeto("daos.persona", DaoPersona.class);
	
	public static final Logger LOG = Logger.getLogger(AnonimoNegocioImpl.class.getName());
	
	@Override
	public Iterable<Persona> obtenerPersonas() {
		return DAO_PERSONA.obtenerTodos();
	}

	@Override
	public boolean autenticar(String usuario, String password) {
		var autenticado = "javier@email.net".equals(usuario) && "javier".equals(password);
		
		if(!autenticado) {
			LOG.warning("Intento de login de usuario " + usuario + " incorrecto");
		}
		
		return autenticado;
	}

}
