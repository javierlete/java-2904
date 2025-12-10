package bases;

public class TiposEnumeraciones {
	enum Color {
		ROJO, VERDE, AZUL, NEGRO, BLANCO
	}

	public static void main(String[] args) {
		Color fondo = Color.BLANCO;
		Color tinta = Color.NEGRO;

		System.out.println(fondo);
		System.out.println(tinta);

		String modo = switch (fondo) {
		case BLANCO -> "Claro";
		case NEGRO -> "Oscuro";
		default -> "OTRO";
		};

		System.out.println(modo);
		
		String sPrimario = "ROJO";
		
		Color primario = Color.valueOf(sPrimario);
		
		System.out.println(primario);
	}
}
