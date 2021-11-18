package coldwar;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import coldwar.utildb.DatosBDD;
import coldwar.utildb.DatosXML;

public class ColdWarMain extends JFrame implements ActionListener{
	
	public ColdWarMain() {
		setTitle("Doctor Strange in the Multiverse of Madness");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(crearMenu());
		setSize(300, 300);
		setVisible(true);
		setResizable(false);
	}
	
	
	
	public JPanel crearMenu() {
		JButton btnJugar = new JButton("Nueva partida");
		btnJugar.addActionListener(this);
		btnJugar.setName("jugar");
		JButton btnReglas = new JButton("Reglas juego");
		btnReglas.addActionListener(this);
		btnReglas.setName("reglas");
		JButton btnInfo = new JButton("Información");
		btnInfo.addActionListener(this);
		btnInfo.setName("Info");
		JButton btnRannking = new JButton("Rangking");
		btnRannking.addActionListener(this);
		btnRannking.setName("Rangking");
		JButton btnCargar = new JButton("Cargar partida");
		btnCargar.addActionListener(this);
		btnCargar.setName("cargar");
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setName("salir");
		
		JPanel menu = new JPanel(new GridLayout(6,1));
		
        menu.setBorder(new EmptyBorder(new Insets(10, 20, 50, 20)));
        menu.add(btnJugar);
		menu.add(btnReglas);
		menu.add(btnInfo);
		menu.add(btnRannking);
		menu.add(btnCargar);
		menu.add(btnSalir);
        
        return menu;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new ColdWarMain();
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) arg0.getSource();
	    if (btn.getName().equals("jugar")) {
             new Jugar(new Partida(), false);
		}else if (btn.getName().equals("reglas")) {
			 new ReglasJuego();
		}else if (btn.getName().equals("Info")) {
			 new Informacion();
		}else if (btn.getName().equals("Rangking")) {
			    new Ranking();
		}else if (btn.getName().equals("cargar")) {
			Partida p = DatosBDD.cargarPartida();
			if (p != null) {
			    new Jugar(p, true);
			}else {
				System.out.println("No hay partida que podamos cargar.");
			}
		}else if (btn.getName().equals("salir")) {
			this.dispose();
		}
			
	}

}


