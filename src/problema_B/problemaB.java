package problema_B;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class problemaB {

	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(calcularProblema(br));
	
	}
	
	private static String calcularProblema(BufferedReader br) {
		try {
			String rta ="";
			
			Integer pp = null;
			Integer pn = null;
			Integer[] plistaValores = null; //Lista que contiene las tareas a ejuecutar
			
			String plinea = "";
			String[] plineaSplited =null;
			
			Integer pnentrep = null;
			LinkedList [] pposiciones = null; //Lista que contiene las posiciones de cada procesador
			
			Integer ppAct = 0;
			Integer pcontadorNentreP = 0; 
			
			Integer[] plistaPesosP = null; //Lista que contiene la carga de cada procesador
			
			TreeSet<Nodo> parbol = null; //Arbol con carga de procesadores
			while(true) {
				ppAct = 0;
				pcontadorNentreP = 0; 
				plinea = br.readLine();
				plineaSplited= plinea.split(" ");
				pn=Integer.parseInt(plineaSplited[0]);
				pp=Integer.parseInt(plineaSplited[1]);
				if(pn==pp && pn==0) return rta;
				plinea = br.readLine();
				plineaSplited = plinea.split(" ");
				plistaValores = new Integer[plineaSplited.length];
				pnentrep = pn/pp;
				pposiciones = new LinkedList[pp];
				pposiciones[ppAct] = new LinkedList<Integer>();
				plistaPesosP = new Integer[pp];
				parbol = new TreeSet<Nodo>();
				for (int i = 0 ; i<pn; i++) {
					plistaValores[i] = Integer.parseInt(plineaSplited[i]);
					pposiciones[ppAct].addLast(i);
					if(plistaPesosP[ppAct]==null) plistaPesosP[ppAct] = 0;
					plistaPesosP [ppAct] += plistaValores[i];
					pcontadorNentreP++;
					if(pcontadorNentreP==pnentrep && ppAct<pp-1) {
						Nodo agregado = new Nodo(plistaPesosP[ppAct], ppAct);
						parbol.add(agregado);
						ppAct++;
						pcontadorNentreP = 0;
						pposiciones[ppAct] = new LinkedList<Integer>();
					}
				}
				parbol.add(new Nodo(plistaPesosP[ppAct], ppAct));
				rta += "\n" + calcularCamin(pn,pp,plistaValores, parbol, plistaPesosP, pposiciones);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "ERROR : " + e.getMessage();
		}
		
	}
	
	static Integer n;
	static Integer p;
	static Integer[] listaValores;
	static TreeSet<Nodo> arbol;
	static Integer [] listaPesosP;
	static LinkedList [] posiciones;
	
	private static int calcularCamin (Integer pn, Integer pp, Integer[] plistaValores, TreeSet<Nodo> parbol,Integer[] plistaPesosP, LinkedList [] pposiciones){ 
		n = pn;
		p = pp;
		listaValores = plistaValores;
		arbol = parbol;
		
		//imprimirArbol(arbol);
		
		listaPesosP = plistaPesosP;
		posiciones = pposiciones;
		TreeSet <Nodo> copiaArbol = new TreeSet <Nodo>();
		copiaArbol = parbol;
		int resultado = -1;
		while(true) {
			Nodo menor = copiaArbol.first();
			resultado = ejecutar(menor.darP(), resultado);
			if(resultado==-1)
			{
				if(copiaArbol.size()!=1) copiaArbol.remove(menor);
				else {

					//System.out.println("\n DEBUG : \n");
					return copiaArbol.first().darK();
				}
			}else {
				copiaArbol=(TreeSet<Nodo>) arbol.clone();
			}
		}
		
		
	}
	
	private static int ejecutar(Integer pAct, Integer ultMod) {
		int kMax = arbol.last().darK();
		boolean esDerecha = false;
		boolean esBorde = false;
		if(pAct==0 && posiciones[pAct+1].size()>1) {esDerecha = true; esBorde=true;}
		else if(pAct==0) return -1;
		else if (pAct==p-1 && posiciones[pAct-1].size()>1) {esDerecha=false;esBorde=true;}
		else if(pAct==p-1) return -1;
		else {
			if(listaPesosP[pAct-1]>listaPesosP[pAct+1] && posiciones[pAct-1].size()>1)esDerecha = false;
			else if (posiciones[pAct+1].size()>1){
				esDerecha = true;
			}else {
				return -1;
			}
		}
		if(esDerecha) {
			int rta = ejecutarDerecha(pAct, kMax ,ultMod);
			if(rta == -1 && !esBorde && posiciones[pAct-1].size()>1)  rta = ejecutarIzquierda(pAct, kMax, ultMod);
			return rta;
		}else {
			int rta = ejecutarIzquierda(pAct, kMax, ultMod);
			if(rta == -1 && !esBorde && posiciones[pAct+1].size()>1)  rta = ejecutarDerecha(pAct, kMax , ultMod);
			return rta;
		}
	}
	
	private static int ejecutarDerecha(Integer pAct, Integer kMax, Integer ultMod) {
		int i = (Integer)posiciones[pAct+1].getFirst();
		int posibleValor = listaValores[i] + listaPesosP[pAct];
		if(posibleValor<kMax && i!=ultMod) {
			cambioPosicion(pAct, true, i);
			return i;
		}
		return -1;
	}
	
	private static void imprimirArbol (TreeSet<Nodo> arbol) {
		Object[] objetos = arbol.toArray();
		for (Object object : objetos) {
			System.out.println(object.toString());
		}
	}

	private static int ejecutarIzquierda(Integer pAct, Integer kMax, Integer ultMod) {
		int i = (Integer)posiciones[pAct-1].getLast();
		int posibleValor = listaValores[i] + listaPesosP[pAct];
		if(posibleValor<kMax && i!=ultMod) {
			cambioPosicion(pAct, false, i);
			return i;
		}
		return -1;
	}
	
	private static void cambioPosicion(Integer pAct, boolean esDerecha, Integer posicion) {
		Integer pesoPosicion = listaValores[posicion];
		Integer pEliminar = pAct-1;
		if(esDerecha) {
			pEliminar = pAct+1;
			posiciones[pEliminar].removeFirst();
			posiciones[pAct].addLast(posicion);
		}
		else {
			posiciones[pEliminar].removeLast();
			posiciones[pAct].addFirst(posicion);
		}
		System.out.println("antes:");
		imprimirArbol(arbol);
		actualizarProcesador(pAct, listaPesosP[pAct]+pesoPosicion, listaPesosP[pAct]);
		actualizarProcesador(pEliminar, listaPesosP[pEliminar]-pesoPosicion, listaPesosP[pEliminar]);
		System.out.println("despues:");
		imprimirArbol(arbol);
	}
	
	
	private static void actualizarProcesador (Integer p, Integer k, Integer kAnterior) {
		
		listaPesosP [p] = k;
		arbol.remove(new Nodo(kAnterior, p));
		arbol.add(new Nodo(k,p));
		
	}
	
	
	
	
	
	private static class Nodo implements Comparable{
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
