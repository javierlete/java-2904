package pruebas;

import java.util.Arrays;

import oop.EstadoCivil;

public class EstadoCivilPrueba {
	public static void main(String[] args) {
		EstadoCivil estadoCivil = EstadoCivil.SOLTERO;

		System.out.println(Arrays.toString(EstadoCivil.values()));

		estadoCivil = EstadoCivil.valueOf("CASADO");

		System.out.println(EstadoCivil.DIVORCIADO);

		System.out.println(estadoCivil.toString());

		System.out.println(switch (estadoCivil) {
		case SOLTERO -> "Te pegas la vida padre";
		case CASADO -> "Se acabó la buena vida";
		case DIVORCIADO -> "Vuelta a la vida padre";
		});
	}
}
