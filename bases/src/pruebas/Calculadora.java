package pruebas;

import java.util.function.BinaryOperator;

public class Calculadora {
	public static void main(String[] args) {
		int op1 = 12, op2 = 6, resultado;

		Operacion op;

		op = new Sumar();

		resultado = op.calcular(op1, op2);

		System.out.println(resultado);

		op = new Restar();

		resultado = op.calcular(op1, op2);

		System.out.println(resultado);

		op = new Operacion() {
			@Override
			public int calcular(int op1, int op2) {
				return op1 * op2;
			}
		};
		
		resultado = op.calcular(op1, op2);
		
		System.out.println(resultado);
		
		op = (a, b) -> a / b;
		
		resultado = op.calcular(op1, op2);
		
		System.out.println(resultado);
		
		BinaryOperator<Integer> operacion;
		
		operacion = (a, b) -> (int)Math.pow(a, b);
		
		System.out.println(operacion.apply(5, 3));
	}

	private interface Operacion {
		int calcular(int op1, int op2);
	}

	private static class Sumar implements Operacion {
		@Override
		public int calcular(int op1, int op2) {
			return op1 + op2;
		}
	}

	private static class Restar implements Operacion {
		@Override
		public int calcular(int op1, int op2) {
			return op1 - op2;
		}
	}
}
