package com.ipartek.formacion.ejemplos.negocio;

import oop.Persona;

public interface AnonimoNegocio {
	Iterable<Persona> obtenerPersonas();
	
	boolean autenticar(String usuario, String password);
}
