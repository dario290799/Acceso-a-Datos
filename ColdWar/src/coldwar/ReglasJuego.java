package coldwar;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReglasJuego extends JFrame{
	public ReglasJuego() {
		 JPanel p = new JPanel();
    	 BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
    	 p.setLayout(box);
    	 p.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
         JLabel l = new JLabel(""
         		+ "<html>"
         		+ "<body>"
         		+ "<h2> Reglas </h2>"
         		+ "<br>"
         		+ "<p>Es un juego de guerra con 5 equipos.</p>"
         		+ "<p>Cada equipo tiene unas vidas limitadas y unos misiles para atacar a los otros equipos.</p>"
         		+ "<p>los ataques de sucederan en orden y puedes decidir si usar los misiles para atacar "
         		+ "o guardar algunos de estos para defenderte.</p>"
         		+ "</body>"
         		+ " </html>"
        		 );
         p.add(l);
         add(p);
         setTitle("Reglas" );
         setSize(300, 300);
 		 setVisible(true);
 		 setResizable(false);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
