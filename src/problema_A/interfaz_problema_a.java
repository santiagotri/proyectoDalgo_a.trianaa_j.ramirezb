package problema_A;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class interfaz_problema_a extends JFrame implements ActionListener{


	private static Problema_a problema_a;


	private JTextArea textAreaEntrada;
	private JTextArea textAreaSalida;

	private JPanel panel;
	private JPanel panelBotones;

	private JButton calcular;

	private JButton informacion;
	private JLabel labelvacio;

	private JButton generarAleatorio;

	public interfaz_problema_a() {
		labelvacio = new JLabel("");
		problema_a = new Problema_a();
		calcular = new JButton("Calcular");
		informacion = new JButton("Acerca de...");
		generarAleatorio = new JButton("Generar matriz aleatoria");

		panel = new JPanel(new GridLayout(1,6));
		panel.setBorder (new TitledBorder ("Panel de información"));
		setLayout( new BorderLayout( ) );
		
		panelBotones = new JPanel(new GridLayout(1,2));


		textAreaSalida = new JTextArea("Aquí sale el resultado de las operaciones");
		textAreaSalida.setEditable(false);


		textAreaEntrada = new JTextArea("Aquí debe introducir la entrada");
		textAreaEntrada.setEditable(true);

		panel.add( new JScrollPane(textAreaEntrada));
		panel.add( new JScrollPane(textAreaSalida));

		panelBotones.add(labelvacio);
		panelBotones.add(calcular);
		panelBotones.add(labelvacio);
		panelBotones.add(generarAleatorio);
		panelBotones.add(labelvacio);
		panelBotones.add(informacion);
		
		
		
		
		calcular.addActionListener(this);
		generarAleatorio.addActionListener(this);
		informacion.addActionListener(this);

		add(panel, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);



		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Problema A");
		setSize(700,500);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String evento = e.getActionCommand();
		if(evento.equals("Calcular")) {
			problema_a = new Problema_a();
			textAreaSalida.setText(problema_a.calcular_problema(textAreaEntrada.getText()) + "\n\n"+problema_a.getTiemposDeEjecucion());

		}
		if(evento.equals("Generar matriz aleatoria")) {
			textAreaEntrada.setText(generarAleatorio());
		}
		if(evento.equals("Acerca de...")) {
			JOptionPane.showMessageDialog(this, "Realizado por: \n Juan Sebastian Ramirez \n Santiago Triana", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private String generarAleatorio() {
		int n = randInt(1,50);
		int modulo = randInt(2,999);
		int ex = randInt(0,999);
		String rta = n + " "+ ex + " "+ modulo +"\n";
		int[][] a=new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				a[i][j]=randInt(0, 50);
				rta += a[i][j]+" ";
			}
			rta += "\n";
		}
		rta += "0 0 0" + "\n";
		return rta;
	}
	
	
	
	public static int randInt(int min, int max) {
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}



