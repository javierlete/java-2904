package oop;

import java.util.ArrayList;

public class Interfaces {
	public static void main(String[] args) {
		var saco = new ArrayList<Rodable>();

		saco.add(new Naranja());
		saco.add(new BalonEsferico());

		for (var rodable : saco) {
			if (rodable instanceof Comestible comestible) {
				comestible.comer();
			}

			rodable.rodar();

			if (rodable instanceof Comestible comestible) {
				comestible.comer();
			}
		}
	}
}

interface Rodable {
	void rodar();
}

interface Comestible {
	void comer();
}

class Fruto {
}

class ObjetoDeporte {
}

class BalonEsferico extends ObjetoDeporte implements Rodable {

	@Override
	public void rodar() {
		System.out.println("BALON RODANDO");
	}
}

class Naranja extends Fruto implements Comestible, Rodable {

	private boolean porElSuelo = false;

	@Override
	public void rodar() {
		System.out.println("NARANJA RODANDO");

		porElSuelo = true;
	}

	@Override
	public void comer() {
		if (porElSuelo) {
			System.out.println("Joder, que puto asco");
		} else {
			System.out.println("Ñam que rica");
		}
	}

}
