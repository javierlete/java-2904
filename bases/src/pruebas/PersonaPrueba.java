package pruebas;

import java.time.LocalDate;
import java.util.Scanner;

import oop.Persona;

public class PersonaPrueba {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Persona p; // p = null

		p = new Persona();
		
		System.out.println(p.toString());
		
		System.out.println(p.getNombre());
		System.out.println(p.getFechaNacimiento());
		
		System.out.print("Introduce el nombre: ");
		
		p.setNombre(sc.nextLine());
		p.setFechaNacimiento(LocalDate.of(2025, 12, 1));
		
		System.out.println(p);
		
		System.out.println(p.getNombre());
		System.out.println(p.getFechaNacimiento());
		
		Persona p2 = new Persona("    Pedro             	", LocalDate.of(2000, 12, 12));
		
		System.out.println(p2.getNombre());
		
		System.out.println(p2);
		
		Persona p3 = new Persona(p2);
		
		System.out.println(p2.getNombre());
		System.out.println(p3.getNombre());

		p3.setNombre("Pepe");
		
		System.out.println(p2.getNombre());
		System.out.println(p3.getNombre());
		
		System.out.println(p3.getEdad());
		
		sc.close();
	}
}
