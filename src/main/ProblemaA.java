package main;



/**
 * Por: 
 * Santiago Triana 201923265
 * Juan Sebastian Ramirez 201923800
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ProblemaA {
	

	public static String tiempos ="";
	
	public static void main(String[] args) {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println(calcular_problema(br));
			
		} catch (Exception e) {
			System.out.println("ERROR:");
			e.printStackTrace();
		}
		
	}
	
	public static String calcular_problema(BufferedReader br) {
		try {
			int pos = 0;
			String rta = "";
			while(true) {
				String actual = br.readLine();
				String [] lineaAct = actual.split(" ");
				int n = Integer.parseInt(lineaAct[0]);
				int ex =Integer.parseInt(lineaAct[1]);
				int modulo =Integer.parseInt(lineaAct[2]);
				if(n == ex && ex == modulo && modulo == 0) return rta;
				Integer [][] matriz = new Integer[n][n];
				for(int i = 0; i<n; i++) {
					actual = br.readLine();
					lineaAct = actual.split(" ");
					for(int j = 0; j<n; j++) {
						matriz [i][j] = Integer.parseInt(lineaAct[j]);
					}
					pos++;
				}
				rta += ejecutarParaMatriz(matriz, ex, modulo);
			}
			
		}catch (Exception e) {
			return "Ha existido un error: " +e.getCause();
		}
		
	}
	
	
	public static String ejecutarParaMatriz (Integer [][] lista, int exp, int modulo) {
		lista = calcularExponenteModulo(lista, exp, lista.length, modulo);
		return imprimirMatriz(lista);
	}
	
	public static Integer[][] calcularExponente( Integer[][] matriz, int ex, int n){
		if(ex==1) return matriz;
		else if (ex%2==1){
			return (multiplicarMatriz(matriz, calcularExponente(matriz, ex-1,n ), n));
		}
		else if(ex%2==0) {
			return multiplicarMatriz(calcularExponente(matriz, ex/2, n), calcularExponente(matriz, ex/2, n), n);
		}
		return null;
	}
	
	public static Integer[][] calcularExponenteModulo( Integer[][] matriz, int ex, int n, int modulo){
		if(ex==1) return matriz;
		else if (ex%2==1){
			return (multiplicarMatrizModulo(matriz, calcularExponenteModulo(matriz, ex-1,n,modulo ), n, modulo));
		}
		else if(ex%2==0) {
			return multiplicarMatrizModulo(calcularExponenteModulo(matriz, ex/2, n, modulo), calcularExponenteModulo(matriz, ex/2, n, modulo), n, modulo);
		}
		return null;
	}
	
	
	// lista [filas][columnas]

	public static Integer[][] multiplicarMatriz(Integer[][] A, Integer[][] B, int n) {
		Integer[][] rta = new Integer [n][n];
		for(int fil=0;fil<n;fil++){    
			for(int col=0;col<n;col++){    
				rta[fil][col]=0;      
				for(int pos=0;pos<n;pos++)      
				{      
					rta[fil][col]+=A[fil][pos]*B[pos][col]; 
				}

			}
		}
		return rta;
	}
	
	
	public static Integer[][] multiplicarMatrizModulo(Integer[][] A, Integer[][] B, int n, int modulo) {
		Integer[][] rta = new Integer [n][n];
		for(int fil=0;fil<n;fil++){    
			for(int col=0;col<n;col++){    
				rta[fil][col]=0;      
				for(int pos=0;pos<n;pos++)      
				{      
					rta[fil][col]+=((A[fil][pos]%modulo)*(B[pos][col]%modulo))%modulo; 
				}
				rta[fil][col]=(rta[fil][col]%modulo); 
			}
		}
		return rta;
	}
	
	public static String imprimirMatriz (Integer[][] matriz) {
		String rta = "";
		for(int filas = 0; filas<matriz.length;filas++) {
			for (int columnas = 0; columnas<matriz[0].length;columnas++) {
				rta += matriz[filas][columnas] + " ";
			}
			rta+= "\n";
		}
		return rta;
	}

	

}
