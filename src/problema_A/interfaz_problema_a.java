package problema_A;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class interfaz_problema_a extends JFrame implements ActionListener{

	
	private JTextArea textAreaEntrada;
	private JTextArea textAreaSalida;
	
	private JPanel panel;
	
	public interfaz_problema_a() {
		panel = new JPanel(new BorderLayout());
		panel.setBorder (new TitledBorder ("Panel de información"));
        setLayout( new BorderLayout( ) );
        
        
        textAreaSalida = new JTextArea("Aquí sale el resultado de las operaciones");
        textAreaSalida.setEditable(false);
       
   
        textAreaEntrada = new JTextArea("Aquí debe introducir la entrada");
        textAreaEntrada.setEditable(true);
        
        add( new JScrollPane(textAreaSalida), BorderLayout.EAST);
        add( new JScrollPane(textAreaEntrada), BorderLayout.WEST);
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
