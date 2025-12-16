package pruebas;

import java.time.LocalDate;

import oop.Empleado;
import oop.Local;
import oop.Persona;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		LocalDate fecha = LocalDate.of(2007, 12, 17);
		
		if(!Empleado.isMayorDeEdad(fecha)) {
			System.out.println("La fecha es de alguien menor de edad");
			return;
		}
		
		Empleado e = new Empleado("Javier", fecha, "X2345678A", null);

		System.out.println(e.getNombre());

		e.setNombre("Otro");

		System.out.println(e);

		Persona p = e; // Casting implícito

		// System.out.println(p.getDni());

		Empleado e2 = (Empleado) p; // Casting explícito

		System.out.println(e2.getDni());

		Persona pepillo = new Persona("Pepillo");
		
		if (pepillo instanceof Empleado) {
			Empleado e3 = (Empleado) pepillo;

			System.out.println(e3.getDni());
		} else {
			System.out.println("Pepillo no es de tipo Empleado");
		}
		
		Local l = new Local("Bilbao", e);

		l.entrar(e);
		l.entrar(new Persona("Pepe"));

		for (Persona per : l.getPersonas()) {
			System.out.printf("Nombre: %s", per.getNombre());

			if(per instanceof Empleado emp) {
				System.out.printf(" DNI: %s", emp.getDni());
			}
			
			System.out.println();
		}
	}
}
