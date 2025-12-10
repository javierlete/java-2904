package bases;

import static java.lang.Math.*;

public class Operadores {
	public static void main(String[] args) {
		System.out.println(5^2);
		System.out.println(pow(5, 2));
		
		int x = 0;
		
		System.out.println(x++); // 0
		System.out.println(x); // 1
		
		x = 0;
		
		System.out.println(++x); // 1
		System.out.println(x); // 1
		
		boolean encendido = false;
		
		System.out.println(encendido ? "ENCENDIDO" : "APAGADO");
		
		int a = 10, b = 6;
		
		System.out.println(a > b ? a : b);
	}
}
