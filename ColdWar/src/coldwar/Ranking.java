package coldwar;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import coldwar.utildb.DatosXML;

public class Ranking extends JFrame{
    public Ranking() {
        JPanel p = new JPanel();
   	    BoxLayout box = new BoxLayout(p, BoxLayout.Y_AXIS);
   	    p.setLayout(box);
   	    p.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        JLabel l = new JLabel(""
        		+ "<html>"
        		+ "<body>"
        		+ "<h2> Ranking </h2>"
        		+ "<br>"
        		+ "<ol>"
        		+ DatosXML.getRannking()
        		+ "</ol>"
        		+ "</body>"
        		+ " </html>"
       		 );
        p.add(l);
        add(p);
        setTitle("Rannking" );
        setSize(300, 300);
	    setVisible(true);
		setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    }
}
