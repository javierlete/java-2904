package bases;

import java.util.ArrayList;
import java.util.HashMap;

public class Colecciones {
	public static void main(String[] args) {
		var numeros = new ArrayList<Integer>();

		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		System.out.println(numeros);
		System.out.println(numeros.get(2));

		var diccionario = new HashMap<String, String>();

		diccionario.put("casa", "house");
		diccionario.put("perro", "dog");
		diccionario.put("cocina", "kitchen");

		System.out.println(diccionario);

		System.out.println(diccionario.get("perro"));

		System.out.println(
				diccionario.entrySet().stream().filter(e -> e.getValue().equals("kitchen")).findFirst().get().getKey());
	}
}
