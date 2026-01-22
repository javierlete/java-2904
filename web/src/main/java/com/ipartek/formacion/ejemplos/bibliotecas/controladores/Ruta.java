package com.ipartek.formacion.ejemplos.bibliotecas.controladores;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Ruta {
	String value();
}
