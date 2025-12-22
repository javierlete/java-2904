package pruebas;

import java.util.Collection;
import java.util.Date;

import fabrica.Fabrica;

public class FabricaPrueba {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		var hoy = Fabrica.getObjeto("hoy", Date.class);
		
		System.out.println(hoy);
		
		var coleccion = Fabrica.getObjeto("lista", Collection.class);
		
		coleccion.add("Hola");
		coleccion.add("Pepe");
		
		System.out.println(coleccion);
	}
}
