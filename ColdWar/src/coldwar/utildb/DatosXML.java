package coldwar.utildb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.w3c.dom.Node;

public class DatosXML {
	
	public static final String fichero_ranking = "ganadores.xml";
    public static void guardarGanador(String ganador) {
    	try {
    		  File f = new File(fichero_ranking);
    		  if (!f.exists()) {
    			  crearRangking(f);
    		  }
    		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    		  Document doc = dBuilder.parse("ganadores.xml");
    		  doc.getDocumentElement().normalize();
    		  //Main Node
              Element raiz = doc.getDocumentElement();
      		  Element ganador_nodo = doc.createElement("ganador");
      		  ganador_nodo.setTextContent(ganador);
      		  raiz.appendChild(ganador_nodo);
    		  guardarXML(doc);
    		} catch(Exception e) {
    		  e.printStackTrace();
    		}
    }
    

	private static void crearRangking(File f) {
		
		try (FileOutputStream fos = new FileOutputStream(f);){
			
  		      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
  		      bw.write("<ganadores></ganadores>");
  		      bw.close();
  		      System.out.println("Creado con exito.");
  		  
  		} catch(Exception e) {
  		   e.printStackTrace();
  		}
	}
	
	
	private static void guardarXML(Document doc) throws TransformerException {
		
		  TransformerFactory transformerFactory = TransformerFactory.newInstance();
		  Transformer transformer = transformerFactory.newTransformer();
		  DOMSource source = new DOMSource(doc);
		  StreamResult result = new StreamResult(new File(fichero_ranking));
		  transformer.transform(source, result);
	}
	
	
	public static String getRannking() {
		String result = "<li>No hay datos aun</>";
		Map<String, Integer> rank = new HashMap<String, Integer>();
		
		try {
		  File f = new File(fichero_ranking);
		  if (!f.exists()) {
			  crearRangking(f);
		  }
		  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		  Document doc = dBuilder.parse("ganadores.xml");
		  doc.getDocumentElement().normalize();
		  Element raiz = doc.getDocumentElement();
		  
		  NodeList ls = raiz.getChildNodes();
		  for(int temp = 0; temp < ls.getLength(); temp++) {
			  Node n = ls.item(temp);
			  if (n.getNodeType() == Node.ELEMENT_NODE) {
				  String nombre = n.getTextContent();
				  if (!rank.containsKey(nombre)) {
					  rank.put(nombre, 1);
				  }else {
					  rank.put(nombre, rank.get(nombre)+1);
				  }
			  }
		  }
		} catch(Exception e) {
		  e.printStackTrace();
		}
		
		if(rank.size() > 0) {
	    	rank  = sortByValue(rank);
		    result = "";
	    	for (Map.Entry<String, Integer> en: rank.entrySet() ) {
		    	result += "<li> Nombre: "+en.getKey() +" Victorias: "+ en.getValue()+ "</li>";
		     }
		}
		
		return result;
	
	}
	
	public static HashMap<String, Integer> sortByValue(Map<String, Integer> rank)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
               new LinkedList<Map.Entry<String, Integer>>(rank.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
            	int cp = (o1.getValue()).compareTo(o2.getValue());
            	if (cp == 1) {
            		cp = -1;
            	}else if (cp == -1) {
            		cp = 1;
            	}
                return (cp);
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
