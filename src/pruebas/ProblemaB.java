package pruebas;
import java.io.BufferedReader;


import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Por: 
 * Santiago Triana 201923265
 * Juan Sebastian Ramirez 201923800
 */
public class ProblemaB {

    public static void main(String[] args) throws Exception {
    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    	System.out.println(problemaB(br));
	}
    
    
    private static String problemaB (BufferedReader br) {
    	try { 
			String lineaAct = br.readLine();
			Integer n = null;
			Integer p = null;
			String [] lineaSplited =null;
			String rta = "";
			String [] listaPesos = null;
			Integer[] listaValores = null;
			Integer suma = null;
			Integer mayor =null;
				
			while(true) {
				lineaSplited = lineaAct.split(" ");
                n = Integer.parseInt(lineaSplited[0]);
                p = Integer.parseInt(lineaSplited[1]);
                if(n == p && n == 0) return rta;
                lineaAct = br.readLine();
                listaPesos = lineaAct.split(" ");
                listaValores = new Integer[n];
                suma = 0;
                mayor = 0;
                
                for(int i=0; i<n;i++) {
                	int act = Integer.parseInt(listaPesos[i]);
                	listaValores[i] = act;
                	suma += act;
                	if(act>mayor) mayor = act;
                }
   
                rta += "\n" + (resolver(listaValores,n,p, suma, mayor));

                lineaAct = br.readLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR: " + e.getMessage();
		}
    }
    
    private static Integer calcularPMax(Integer listaValores[], Integer maximo){
    	Integer rta = 0;
    	Integer sumaAct = 0;
        for (int i = 0; i<listaValores.length; i++) {
        	sumaAct += listaValores[i];
            if(sumaAct > maximo){
            	sumaAct = listaValores[i];
                ++rta;
            } 
        }
        ++rta; //procesador de ultimos valores que no suman maximo
        return rta;
    }  

    private static Integer resolver(Integer listaValores[], Integer n, Integer p, Integer suma, Integer mayor){
    	Integer maximo = 0;
    	if(p == n) return mayor;
    	if(p == 1) return suma;
        while(mayor < suma){
            maximo = mayor + (suma-mayor)/2;
            if(calcularPMax(listaValores, maximo) <= p) suma = maximo;
            else {mayor = ++maximo;}
        }
        return mayor;
    }

}