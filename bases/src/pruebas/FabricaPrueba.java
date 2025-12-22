package pruebas;

import java.util.Collection;
import java.util.Date;

import fabrica.Fabrica;

public class FabricaPrueba {
	public static void main(String[] args) {
		var hoy = (Date) Fabrica.getObjeto("hoy");
		
		System.out.println(hoy);
		
		@SuppressWarnings("unchecked")
		var coleccion = (Collection<String>) Fabrica.getObjeto("lista");
		
		coleccion.add("Hola");
		coleccion.add("Pepe");
		
		System.out.println(coleccion);
	}
}
