package pruebas;

import oop.Decorado;

@Decorado(tipo = "Constante")
public class DecoradoPrueba {
	@Decorado
	private static final String PRUEBA = "PRUEBA";

	@Decorado
	public static void main(String[] args) {
		System.out.println(DecoradoPrueba.class.getAnnotationsByType(Decorado.class)[0].tipo());
	}
}
