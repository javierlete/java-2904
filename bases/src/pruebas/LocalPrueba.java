package pruebas;

import oop.Local;
import oop.Persona;

public class LocalPrueba {
	public static void main(String[] args) {
		var responsable = new Persona("Javier");
		var local = new Local("Bilbao", responsable);
		
		local.entrar(responsable);
		
		local.entrar(new Persona("Pepe"));
		local.entrar(new Persona("Juan"));
		
		local.salir(responsable);
		local.salir(new Persona("Juan"));
		
		System.out.println(local);
		
		for(var persona: local.getPersonas()) {
			System.out.println(persona);
		}
	
		System.out.println(new Persona("Pedro") == new Persona("Pedro"));
		System.out.println(new Persona("Pedro").equals(new Persona("Pedro")));
		
	}
}
