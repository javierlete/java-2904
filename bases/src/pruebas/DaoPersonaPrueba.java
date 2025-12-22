package pruebas;

import java.time.Duration;
import java.time.LocalTime;

import daos.DaoPersona;
import daos.DaoPersonaMap;
import oop.Persona;

public class DaoPersonaPrueba {
	public static void main(String[] args) {
		DaoPersona dao = new DaoPersonaMap();

		var antes = LocalTime.now();

		for (int i = 1; i <= 100000; i++) {
			dao.insertar(new Persona("Persona" + i));
		}

		var despues = LocalTime.now();
		

		for (Persona p : dao.obtenerTodos()) {
			System.out.println(p);
		}

		System.out.println(Duration.between(antes, despues));
	}
}
