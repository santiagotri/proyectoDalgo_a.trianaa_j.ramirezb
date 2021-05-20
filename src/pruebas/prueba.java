package pruebas;

import java.util.*;

public class prueba {

	public static void main(String[] args) {
		
		System.out.println(factors(19));
		// TODO Auto-generated method stub

	}


	private static ArrayList<Integer> factors (Integer n) {
		ArrayList<Integer> f = new ArrayList<Integer>();
		for (int x = 2; x*x <= n; x++) {
			while (n%x == 0) {
				f.add(x);
				n /= x; }
		}
		if (n > 1) f.add(n); return f;
	}



}
