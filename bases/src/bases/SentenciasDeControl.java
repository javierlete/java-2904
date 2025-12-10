package bases;

public class SentenciasDeControl {
	public static void main(String[] args) {
		int dias;

		for (int mes = 1; mes <= 12; mes++) {
			switch (mes) {
			case 2:
				dias = 28;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				dias = 30;
				break;
			default:
				dias = 31;
			}
			System.out.printf("El mes %s tiene %s días\n", mes, dias);
		}

		for (int mes = 1; mes <= 12; mes++) {
			switch (mes) {
			case 2 -> dias = 28;
			case 4, 6, 9, 11 -> dias = 30;
			default -> dias = 31;
			}
			System.out.printf("El mes %s tiene %s días\n", mes, dias);
		}

		for (int mes = 1; mes <= 12; mes++) {
			dias = switch (mes) {
			case 2 -> 28;
			case 4, 6, 9, 11 -> 30;
			default -> 31;
			};

			System.out.printf("El mes %s tiene %s días\n", mes, dias);
		}

		String[] textos = { "Uno", "Dos", "Tres" };

		for (String texto : textos) {
			System.out.println(texto.toUpperCase());
		}
	}
}
