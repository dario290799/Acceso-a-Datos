package coldwar;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Informacion extends JFrame{
    
   public Informacion() {
    	 
    	 JPanel p = new JPanel();
    	 BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
    	 p.setLayout(box);
    	 p.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
         JLabel l = new JLabel(""
         		+ "<html>"
         		+ "<body>"
         		+ "<h2> Imformacion </h2>"
         		+ "<ul>"
         		+ "<li>Version: 1.5 </li>"
         		+ "<li>Contacto: Ilerna.Lleida@gmail.com </li>"
         		+ "<li>Autor: Santi Raymat </li>"
         		+ "</ul>"
         		+ "</body>"
         		+ " </html>"
        		 );
         p.add(l);
         add(p);
         setTitle("Información" );
         setSize(300, 300);
 		 setVisible(true);
 		 setResizable(false);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     }
}
