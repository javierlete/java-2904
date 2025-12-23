package pruebas;

import oop.PersonaDto;

public class PersonaDtoPrueba {
	public static void main(String[] args) {
		PersonaDto dto1 = new PersonaDto(1L, "Javier");
		PersonaDto dto2 = new PersonaDto(1L, "Javier");

		System.out.println(dto1 == dto2);
		System.out.println(dto1.equals(dto2));
		
		System.out.println(dto1.toString());
		System.out.println(dto1.hashCode());
		System.out.println(dto2.hashCode());
		
		System.out.println(dto1.id());
		System.out.println(dto1.nombre());
	}
}
