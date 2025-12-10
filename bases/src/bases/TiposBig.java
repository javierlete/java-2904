package bases;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class TiposBig {
	public static void main(String[] args) {
		long l = Long.MAX_VALUE;

		System.out.println(++l);

		System.out.println(factorial(30));

		System.out.println(factorialRecursivo(5));

		System.out.println(factorial(new BigInteger("100000")).toString().length());

		double d1 = 0.1, d2 = 0.2, suma = d1 + d2;

		System.out.println(suma);

		BigDecimal bd1 = new BigDecimal("0.1");
		BigDecimal bd2 = new BigDecimal("0.2");

		BigDecimal bdSuma = bd1.add(bd2);

		System.out.println(bd1);
		System.out.println(bd2);

		System.out.println(bdSuma);

		System.out.println(10.0 / 3.0);
		
		System.out.println(new BigDecimal("10.0").divide(new BigDecimal("3.0"), 30, RoundingMode.HALF_UP));

	}

	private static BigInteger factorial(BigInteger bi) {
		BigInteger total = bi.subtract(BigInteger.ONE);

		bi = bi.subtract(BigInteger.ONE);

		for (; bi.compareTo(BigInteger.ZERO) > 0; bi = bi.subtract(BigInteger.ONE)) {
			total = total.multiply(bi);
		}

		return total;
	}

	/*
	 * 5! = 5 * 4 * 3 * 2 * 1
	 */
	private static long factorial(long l) {
		long total = l--;

		for (; l > 0; l--) {
			total *= l;
		}

		return total;
	}

	/*
	 * 5! = 5 * 4! 4! = 4 * 3! 3! = 3 * 2! 2! = 2 * 1! 1! = 1
	 */
	private static long factorialRecursivo(long l) {
		if (l == 1) {
			return 1;
		}

		return l * factorialRecursivo(l - 1);
	}
}
