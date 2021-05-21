package problema_A;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class interfaz_problema_a extends JPanel implements ActionListener{

	
	private JTextArea textAreaEntrada;
	private JTextArea textAreaSalida;
	
	public interfaz_problema_a() {
		setBorder (new TitledBorder ("Panel de información"));
        setLayout( new BorderLayout( ) );
        
        textAreaSalida = new JTextArea("Aquí sale el resultado de las operaciones");
        textAreaSalida.setEditable(false);
        add (new JScrollPane(textAreaSalida), BorderLayout.CENTER);
        
        textAreaSalida = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
        textAreaSalida.setEditable(false);
        add (new JScrollPane(textAreaSalida), BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
