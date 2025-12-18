package pruebas;

import daos.DaoPersona;
import daos.DaoPersonaCollection;
import oop.Persona;

public class DaoPersonaPrueba {
	public static void main(String[] args) {
		DaoPersona dao = new DaoPersonaCollection();
		
		dao.insertar(new Persona("Javier"));
		dao.insertar(new Persona("Pepe"));
		
		for(Persona p: dao.obtenerTodos()) {
			System.out.println(p);
		}
	}
}
