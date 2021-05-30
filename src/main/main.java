package main;

import javax.swing.JOptionPane;

import problema_A.Problema_a;
import problema_A.interfaz_problema_a;

public class main {
	
	private static Problema_a problema_a;
	private static interfaz_problema_a interfaz_problema_a;

	public static void main(String[] args) {
		String[] botones = { "Problema A", "Problema B"};
	    int rta = JOptionPane.showOptionDialog(null, "¿Que solución desea correr?", "Solucion?",
	        JOptionPane.INFORMATION_MESSAGE, 0, null, botones, botones[0]);
	    if(rta==0) interfaz_problema_a = new interfaz_problema_a();
	    else if(rta==1);
	}
	

}
