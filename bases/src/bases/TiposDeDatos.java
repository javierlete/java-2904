package bases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

public class TiposDeDatos {
	public static void main(String[] args) {
		long l = 2147483648L;

		System.out.println(l);

		double d1 = 0.1;
		double d2 = 0.2;

		double suma = d1 + d2;

		System.out.println(suma);

//		int valorInt = null;
		Integer referenciaInteger = null;

//		System.out.println(referenciaInteger * 2);

		referenciaInteger = 6;

		System.out.println(referenciaInteger * 2);

		Optional<Integer> enteroOpcional = Optional.empty();

		enteroOpcional = Optional.of(5);

		if (enteroOpcional.isPresent()) {
			System.out.println(enteroOpcional.get() * 2);
		} else {
			System.out.println("No tiene valor");
		}

		String texto = """
				En un lugar de la Mancha
				De cuyo nombre no me da la gana de acordarme...
				""";

		System.out.println(texto);

		String nombre = "Javier";

		String saludo = String.format("Hola %s, qué tal estás", nombre);

		System.out.println(saludo);

		String nombre2 = "Javier";

		System.out.println(nombre == nombre2); // Compara punteros // Compara si SON EL MISMO OBJETO
		System.out.println(nombre.equals(nombre2)); // Compara contenidos de objetos

		nombre = "Pepe";

		System.out.println(nombre);

		nombre += " Pérez";

		System.out.println(nombre);

		String log = "";

		log += "Primer dato\n"; // new StringBuffer(log).append("Primer dato\n").toString()
		log += "Segundo dato\n"; // new StringBuffer(log).append("Segundo dato\n").toString()
		log += "Tercer dato\n"; // new StringBuffer(log).append("Tercer dato\n").toString()

		System.out.println(log);

		StringBuffer sb = new StringBuffer();

		sb.append("Primer dato\n");
		sb.append("Segundo dato\n");
		sb.append("Tercer dato\n");

		System.out.println(sb.toString());

		LocalDateTime primerDiaCurso = LocalDateTime.of(2025, 12, 9, 15, 0);
		
		System.out.println(primerDiaCurso);
		
		LocalDate fecha = LocalDate.of(2025, 1, 31);
		
		System.out.println(fecha.plusMonths(1));
		
		int tamanyo = 3;
		
		int[] arr = new int[tamanyo];
		
		arr[0] = 5;
		arr[1] = 6;
		arr[2] = 7;
//		arr[3] = 8; // No se pueden poner elementos fuera del array
		
		System.out.println(Arrays.toString(arr));
		
		System.out.println(arr.length);
		
		String[] nombres = { "Pepe", "Pedro", "Javier" };
		
		System.out.println(Arrays.toString(nombres));
	}
}
