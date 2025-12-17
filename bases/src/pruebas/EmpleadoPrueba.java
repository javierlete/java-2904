package pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import oop.Empleado;
import oop.EmpleadoIndefinido;
import oop.EmpleadoPorHoras;
import oop.Local;
import oop.Persona;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		LocalDate fecha = LocalDate.of(2007, 12, 17);

		if (!Empleado.isMayorDeEdad(fecha)) {
			System.out.println("La fecha es de alguien menor de edad");
			return;
		}

		Empleado e = new EmpleadoIndefinido(null, "Javier", fecha, "X2345678A", null, new BigDecimal("23456"), 14);

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
		l.entrar(new EmpleadoPorHoras(null, "Juan", LocalDate.of(2000, 1, 2), "23456789Z", "1234123412341234",
				new BigDecimal("20"), 50));

		for (Persona per : l.getPersonas()) {
			System.out.printf("Nombre: %s", per.getNombre());

			if (per instanceof Empleado emp) {
				System.out.printf(" DNI: %s, SUELDO MENSUAL: %s", emp.getDni(), emp.getSueldoMensual());
			}

			System.out.println();
		}
	}
}
