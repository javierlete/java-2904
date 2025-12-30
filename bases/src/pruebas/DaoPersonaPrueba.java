package pruebas;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import bibliotecas.fabrica.Fabrica;
import daos.DaoPersona;
import oop.Persona;

public class DaoPersonaPrueba {
	public static void main(String[] args) {
		DaoPersona dao = Fabrica.getObjeto("daos.persona", DaoPersona.class);

		var antes = LocalTime.now();

		for (int i = 1; i <= 10; i++) {
			dao.insertar(new Persona("Persona" + i, LocalDate.now()));
		}

		var despues = LocalTime.now();

//		for (Persona p : dao.obtenerTodos()) {
//			System.out.println(p);
//		}

		dao.obtenerTodos().stream().forEach(p -> System.out.println(p));
		
		System.out.println(Duration.between(antes, despues));
		
		System.out.println(dao.getClass().getName());
		
		dao.buscarPorNombre("Persona1000").stream().forEach(System.out::println);
		
		dao.obtenerPersonaDtos().forEach(System.out::println);
	}
}
