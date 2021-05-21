package problema_A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Problema_a extends JFrame implements ActionListener{

	public Problema_a() {
		while (true) {
			Integer [][] lista = solicitarEntrada();
			Integer [][] copia = lista;
			imprimirMatriz(lista);
			
			String nString = JOptionPane.showInputDialog(this, "Ingrese el exponente", "Exponente", JOptionPane.QUESTION_MESSAGE);
			int exp = Integer.parseInt(nString);
			
			nString = JOptionPane.showInputDialog(this, "Ingrese el modulo", "modulo", JOptionPane.QUESTION_MESSAGE);
			int modulo = Integer.parseInt(nString);
			
			long startTime = System.nanoTime();
			

			lista = calcularExponente(lista, exp, lista.length);
			
			for(int i = 0; i<lista.length; i++) {
				for (int j = 0; j<lista.length; j++) {
					lista [i][j] = lista[i][j]%modulo;
				}
			}
			
			long stopTime = System.nanoTime();
			System.out.println(stopTime - startTime + " nanosegundos");
			
			
			imprimirMatriz(lista);
			
			startTime = System.nanoTime();
			
			copia = calcularExponenteModulo(copia, exp, copia.length, modulo);
			
			stopTime = System.nanoTime();
			System.out.println(stopTime - startTime + " nanosegundos");
			
			imprimirMatriz(copia);
			
		}
		
	}
	
	private Integer[][] calcularExponente( Integer[][] matriz, int ex, int n){
		if(ex==1) return matriz;
		else if (ex%2==1){
			return (multiplicarMatriz(matriz, calcularExponente(matriz, ex-1,n ), n));
		}
		else if(ex%2==0) {
			return multiplicarMatriz(calcularExponente(matriz, ex/2, n), calcularExponente(matriz, ex/2, n), n);
		}
		return null;
	}
	
	private Integer[][] calcularExponenteModulo( Integer[][] matriz, int ex, int n, int modulo){
		if(ex==1) return matriz;
		else if (ex%2==1){
			return (multiplicarMatrizModulo(matriz, calcularExponenteModulo(matriz, ex-1,n,modulo ), n, modulo));
		}
		else if(ex%2==0) {
			return multiplicarMatrizModulo(calcularExponenteModulo(matriz, ex/2, n, modulo), calcularExponenteModulo(matriz, ex/2, n, modulo), n, modulo);
		}
		return null;
	}
	
	private Integer[][] solicitarEntrada () {
		String nString = JOptionPane.showInputDialog(this, "Ingrese el tamaño de la matriz cuadrada", "Tamaño matriz", JOptionPane.QUESTION_MESSAGE);
		int n = Integer.parseInt(nString);
		Integer[][] rta = new Integer[n][n];
		for(int i = 0; i<n; i++) {
			nString = JOptionPane.showInputDialog(this, "Ingrese los valores de la fila " + (i+1), "Valor fila " + (i+1), JOptionPane.QUESTION_MESSAGE);
			String[] listaStrings = nString.split(",");
			for(int j = 0; j<n; j++) {
				rta [i][j] = Integer.parseInt(listaStrings[j]);
			}
		}
		return rta;
		
	}

	int n = 2;

	// lista [filas][columnas]

	private Integer[][] multiplicarMatriz(Integer[][] A, Integer[][] B, int n) {
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
	
	
	private Integer[][] multiplicarMatrizModulo(Integer[][] A, Integer[][] B, int n, int modulo) {
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
	
	private void imprimirMatriz (Integer[][] matriz) {
		for(int filas = 0; filas<matriz.length;filas++) {
			String imprimir = "( ";
			for (int columnas = 0; columnas<matriz[0].length;columnas++) {
				imprimir += matriz[filas][columnas] + " ";
			}
			imprimir+= ")";
			System.out.println(imprimir);
		}
		System.out.println(" ");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
