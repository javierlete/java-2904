package pruebas;

import oop.Empleado;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		Empleado e = new Empleado("Javier", null, null, null);
		
		System.out.println(e.getNombre());
		
		e.setNombre("Otro");
		
		System.out.println(e);
	}
}
