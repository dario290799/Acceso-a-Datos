package coldwar;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import coldwar.utildb.DatosBDD;
import coldwar.utildb.DatosXML;

public class Jugar extends JFrame implements ActionListener{
    public CardLayout cl = new CardLayout(20, 20);
	public final int MAX_JUGADORES = 10;
	public final int MIN_JUGADORES = 3;
	
	
	
	JTextField nombre;
	JComboBox combo;
	JButton btnAtacar;
	JLabel info;
	JLabel lblEstado;
	JLabel infMisiles;
	JLabel infAtaques;
	JLabel nombre_planeta;
	JTextField numero_misiles;
	JComboBox contrincantes;
	DefaultListModel<String> modelo = new DefaultListModel<>();
	Partida partida;
	Planeta plAtacante;
	String log = "";
	
	public Jugar(Partida partida, boolean cargar) {
       this.partida = partida;
	   setTitle("Juego");
	   setLayout(cl);
	   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   
	   
	   
	   add(enterJugadores(), "init");
	   add(enterPartida(), "partida");
	   
	   if (cargar) {
		   cl.show(getContentPane(), "partida");
		   cl.show(this.getContentPane(), "partida");
		   log += partida.mostrarEstado();
		   actualizaEstado();
		   plAtacante = partida.getNextVivo();
		   showAtacante(plAtacante);
	   }
	   
	   setSize(650, 450);
	   setVisible(true);
	   setResizable(false);
   }
	
	private JPanel enterPartida() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel pm = new JPanel(new CardLayout(10,10));
		
		JScrollPane js = new JScrollPane();
		lblEstado = new JLabel(partida.mostrarEstado());
		js.setViewportView(lblEstado);
		
		//Creamos los controles del juego
		JPanel pb = new JPanel(new GridLayout(7, 1));
		btnAtacar = new JButton("Atacar");
		btnAtacar.addActionListener(this);
		btnAtacar.setName("ataque");
		
		JButton btnGuardar = new JButton("Guardar partida");
		btnGuardar.addActionListener(this);
		btnGuardar.setName("guardar");
		
		nombre_planeta = new JLabel("Planeta");
	    infMisiles = new JLabel("Tiene X misiles");
	    JLabel lm = new JLabel("Cuantos para atacar");
	    
	    
	    
	    numero_misiles = new JTextField();
	    contrincantes = new JComboBox<>();
	    pb.add(nombre_planeta);
	    pb.add(infMisiles);
	    pb.add(lm);
	    pb.add(numero_misiles);
	    pb.add(contrincantes);
		pb.add(btnAtacar);
		pb.add(btnGuardar);
		
		JPanel p3 = new JPanel(new GridLayout(2,1));
		infAtaques = new JLabel();
		p3.add(pb);
		p3.add(infAtaques);
		
		pm.add(js, "estado");
		mainPanel.add(pm, BorderLayout.CENTER);
		mainPanel.add(p3, BorderLayout.LINE_END);
		
		return mainPanel;
	}

	public JPanel enterJugadores() {
		 
		 GridLayout gp = new GridLayout(1,0);
		 gp.setHgap(10);
		
		 JPanel mainPanel = new JPanel(gp);
		 
		 GridLayout g = new GridLayout(9,1);
		 g.setVgap(10);
		 JPanel pm = new JPanel(g);
		 
		 
		
		
		JLabel l = new JLabel("Nombre planeta");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombre = new JTextField();
		
		JPanel pb = new JPanel(new GridLayout(1,2));
		
		JButton btnAdd = new JButton("Agregar Jugador");
		btnAdd.addActionListener(this);
		btnAdd.setName("add");
		
		JLabel lp = new JLabel("Tipo planeta");
		l.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		combo = new JComboBox();
		combo.addItem("Normal");
		combo.addItem("Rojo");
		combo.addItem("Azul");
		combo.addItem("Verde");
		combo.addItem("Gaseoso");
		combo.addItem("Enano");
		combo.addItem("Gigante");
		combo.addItem("Brillante");
		combo.addItem("Escurridizo");
		combo.addItem("Fiestero");
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(this);
		btnJugar.setName("jugar");
		
		info = new JLabel();
		info.setAlignmentX(Component.CENTER_ALIGNMENT);
		JList lista = new JList();
		lista.setModel(modelo);
		
		JScrollPane jp = new JScrollPane();
		
		jp.setViewportView(lista);
		
		pb.add(btnAdd);
		pb.add(btnJugar);
		
		pm.add(l);
		pm.add(nombre);
		pm.add(lp);
		pm.add(combo);
		pm.add(pb);
		pm.add(info);
		
		mainPanel.add(pm);
		mainPanel.add(jp);
		
		return  mainPanel;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton)arg0.getSource();
		if (btn.getName().equals("add")) {
			
			if (partida.getParticipantes() > MAX_JUGADORES) {
				info.setText("Maximo de jugadores superado.");
			}else if (nombre.getText().isBlank() || nombre.getText().isEmpty()) {
				info.setText("Nombre planeta no valido.");	
			}else {
				 String nombre_planeta = nombre.getText();
				 String tipo = combo.getSelectedObjects()[0].toString();
			     modelo.addElement(nombre_planeta+ "  ("+ tipo +")");
			     partida.add_equipo(new Planeta(nombre_planeta, tipo));
			}
			nombre.setText("");
	    }
		else if (btn.getName() == "jugar") {
			if (partida.getParticipantes() < MIN_JUGADORES) {
				info.setText("Para jugar minimo son "+ MIN_JUGADORES+ " jugadores.");
			}else {
				cl.show(this.getContentPane(), "partida");
				log += partida.mostrarEstado();
				actualizaEstado();
				plAtacante = partida.getNextVivo();
				showAtacante(plAtacante);
			}
			    
		}else if (btn.getName() == "ataque") {
			 try {
				 int num_mil = Integer.parseInt(numero_misiles.getText());
				 if (numero_misiles.getText().isBlank() || numero_misiles.getText().isEmpty() ||
			    		 num_mil > plAtacante.getMisiles_ataque()) {
					     mostrarError();
			     }else {
			    	 Planeta atacado = partida.findPlaneta(contrincantes.getSelectedObjects()[0].toString());
			    	 log += "<br><hr>"+ plAtacante.addAtaque(num_mil, atacado);
			    	 actualizaEstado();
			    	 plAtacante = partida.getNextVivo();
			    	 if(plAtacante!=null)
			    	     showAtacante(plAtacante);
			    	 else {
			    		 partida.nuevaRonda();
			    		 log += partida.mostrarEstado();
						 actualizaEstado();
						 if(partida.getNumVivos() == 1) {
							 log += partida.mostrarFinalPartida();
							 DatosXML.guardarGanador(partida.getGanador().getNombre());
							 actualizaEstado();
							 btnAtacar.setEnabled(false);
						 }else {
							 plAtacante = partida.getNextVivo();
					    	 if(plAtacante!=null)
					    	     showAtacante(plAtacante);
							 
						 }
			    	 }
			    	 
			    	 
			     }
				 
			 }catch(NumberFormatException e) {
				 mostrarError();
			 }
			 
			 
		    
			    
		}else if (btn.getName() == "guardar") {
			
			DatosBDD.guardarPartida(partida);
			this.dispose();
			
		}
	}
	
	public void mostrarError() {
		 infAtaques.setText("<html><body>"
	    	 		+ "<p>Tiene que contener <br> "
	    	 		+ "un numero de misiles valido. </p>"
	    	 		+ "<p> Actual mente cuentas con <br> "+ plAtacante.getMisiles_ataque()+"</p>"
	    	 		+ "</body></html>");
	}
	
	public void actualizaEstado() {
		 lblEstado.setText("<html><body>"+log+"</htm></body>");
	}
	
	
	public void showAtacante(Planeta plAtacante) {
		nombre_planeta.setText("Atacante "+ plAtacante.getNombre());
		infMisiles.setText("Tienes "+plAtacante.getMisiles_ataque()+" para atacar");
		Planeta[] pContrincates = partida.getPlanetas(plAtacante.getNombre());
		contrincantes.removeAllItems();
		numero_misiles.setText("");
		for (int i = 0; i< pContrincates.length; i++) {
			contrincantes.addItem(pContrincates[i].getNombre());
		}
	}
	
	
	
}
