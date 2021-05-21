package problema_A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Problema_a extends JFrame implements ActionListener{

	private String tiemposDeEjecucion  ="(Tiempos de ejecucion)\n";
	public Problema_a() {
		
		
	}
	
	public String getTiemposDeEjecucion() {
		return tiemposDeEjecucion;
	}
	
	public String calcular_problema(String entrada) {
		try {
			String[] entradas = entrada.split("\n");
			int pos = 0;
			String rta = "";
			while(pos<entradas.length) {
				String [] lineaAct = entradas[pos].split(" ");
				int n = Integer.parseInt(lineaAct[0]);
				int ex =Integer.parseInt(lineaAct[1]);
				int modulo =Integer.parseInt(lineaAct[2]);
				if(n == ex && ex == modulo && modulo == 0) return rta;
				pos++;
				Integer [][] matriz = new Integer[n][n];
				for(int i = 0; i<n; i++) {
					lineaAct = entradas[pos].split(" ");
					for(int j = 0; j<n; j++) {
						matriz [i][j] = Integer.parseInt(lineaAct[j]);
					}
					pos++;
				}
				rta += ejecutarParaMatriz(matriz, ex, modulo);
			}	
			return rta;
			
		}catch (Exception e) {
			return "Ha existido un error: " +e.getCause();
		}
		
	}
	
	
	private String ejecutarParaMatriz (Integer [][] lista, int exp, int modulo) {
		long startTime = System.nanoTime();
		lista = calcularExponenteModulo(lista, exp, lista.length, modulo);
		long stopTime = System.nanoTime();
		String tiempo = (stopTime - startTime) + " nanosegundos";
		tiemposDeEjecucion += tiempo + "\n";
		return imprimirMatriz(lista);
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
	
	private String imprimirMatriz (Integer[][] matriz) {
		String rta = "";
		for(int filas = 0; filas<matriz.length;filas++) {
			for (int columnas = 0; columnas<matriz[0].length;columnas++) {
				rta += matriz[filas][columnas] + " ";
			}
			rta+= "\n";
		}
		return rta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
