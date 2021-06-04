package pruebas;

import java.util.*;


public class prueba {

	public static void main(String[] args) {
		
		TreeSetComparable<Nodo> arbol = new TreeSetComparable<Nodo>();
		TreeSet<TreeSetComparable<Nodo>> arbolTuneado = new TreeSet<TreeSetComparable<Nodo>>();
		arbol.add(new Nodo(1,2));
		arbol.add(new Nodo(2,4));
		arbolTuneado.add(arbol);
		System.out.println(arbolTuneado.contains(arbol));
		
	
	}
	
	public static class Nodo implements Comparable{
		private int k = 0;
		private int p = 0;
		public Nodo(int pK, int pP) {
			p=pP;
			k=pK;
		}
		public int darK() {return k;}
		public int darP() {return p;}
		@Override
		public int compareTo(Object o) {
			Nodo comparado = (Nodo)o;
			if(darK()<comparado.darK())return -1;
			else if (darK()>comparado.darK()) return 1;
			else if(darK()==comparado.darK() && darP()<comparado.darP()) return -1;
			else if(darK()==comparado.darK() && darP()>comparado.darP()) return 1;
			else if (darK()==comparado.darK() && darP()==comparado.darP()) return 0; 
			else {return 1;}
		}
		public String toString() { return "k:" + k + ",p:" + p;}
		
	}

	public static class TreeSetComparable<Nodo> extends TreeSet implements Comparable{
		
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Object o) {
			return this.toString().compareTo(o.toString());
		}
		
		public static class Nodo implements Comparable{
			private int k = 0;
			private int p = 0;
			public Nodo(int pK, int pP) {
				p=pP;
				k=pK;
			}
			public int darK() {return k;}
			public int darP() {return p;}
			@Override
			public int compareTo(Object o) {
				Nodo comparado = (Nodo)o;
				if(darK()<comparado.darK())return -1;
				else if (darK()>comparado.darK()) return 1;
				else if(darK()==comparado.darK() && darP()<comparado.darP()) return -1;
				else if(darK()==comparado.darK() && darP()>comparado.darP()) return 1;
				else if (darK()==comparado.darK() && darP()==comparado.darP()) return 0; 
				else {return 1;}
			}
			public String toString() { return "k:" + k + ",p:" + p;}
			
		}
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
