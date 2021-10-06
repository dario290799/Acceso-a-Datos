package coldwar;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Partida {

	
	private int contador_ronda = 1;
	private int puntero = 0;
	
	private ArrayList<Planeta> planetas = new ArrayList<Planeta>();

	private Planeta ganador;
	
	public Planeta getGanador() {
		return ganador;
	}

    public int getParticipantes() {
    	return planetas.size();
    }
   
	
	public Planeta[] getPlanetas() {
		Planeta[] ps = new Planeta[planetas.size()];
		for (int i = 0;i< planetas.size(); i++) {
			ps[i] = planetas.get(i);
		}
		return ps;
	}
	
	//Cojer el primer atacante de la lista que este vivo.
	public Planeta getNextVivo() {
		for(int i = puntero; i< planetas.size();i++) {
			if (planetas.get(i).estaVivo()) {
				puntero=i+1;
				return planetas.get(i);
			}
		}
		return null;
	}

	//Array de planteas vivos menos el atacante.
	public Planeta[] getPlanetas(String nombre) {
		// TODO Auto-generated method stub
		int vivos = getNumVivos();
		Planeta[] ps = new Planeta[vivos-1];
		int i = 0;
		int cont = 0;
		while(cont < planetas.size()) {
			Planeta p = planetas.get(cont);
			if(p.estaVivo() && !p.getNombre().equals(nombre)) {
				ps[i] = p;
				i++;
			}
			cont++;
		}
		
		
		return ps;
	}
	
	
	
	//Buscamos un planeta por su nombre.
	public Planeta findPlaneta(String nombre) {

		for (Planeta p : planetas) {
			if (p.getNombre().equals(nombre)) {
				return p;
			} 
		}
		return null;
	}

	

	// funcion para comprobar los equipos vivos
	public int getNumVivos() {
		int vivos = 0;
		for (Planeta p : planetas) {
			if (p.estaVivo()) {
				vivos++;
			}
		}
		return vivos;
	}
	
	public int getContador_ronda() {
		return contador_ronda;
	}

	public void setContador_ronda(int contador_ronda) {
		this.contador_ronda = contador_ronda;
	}

	public int getPuntero() {
		return puntero;
	}

	public void setPuntero(int puntero) {
		this.puntero = puntero;
	}

	// funcion para comprobar los equipos muertos
	public int getNumMuertos() {
		int muertos = 0;
		for (Planeta p : planetas) {
			if (!p.estaVivo()) {
				muertos++;
			}
		}
		return muertos;
	}
   
    

	// creamos los equipos i los ponemos en planetas array
	public void add_equipo(Planeta p) {
			planetas.add(p);
	}

	// funcion que usamos para ver el estado de la partida
	public String mostrarEstado() {
		String r = "";
		r += "<h3>Equipos vivos: " + getNumVivos() +"</h3>";
		for (Planeta p : planetas) {
			if (p.getVida() > 0) {
			    r += p.showEstado();
			}

		}
		return r;
	}

   public void nuevaRonda() {
	   puntero = 0;
	   contador_ronda++;
	   for (Planeta p : planetas) {
		   p.nuevaRonda(contador_ronda);
	   }
   }
	

	// funcion para mostrar el ganador de cada ronda
	public void mostrarGanador() {
		for (Planeta p : planetas) {
			if (ganador == null) {
				ganador = p;
			} else if (ganador.getVida() < p.getVida()) {
				ganador = p;
			}
		}
		System.out.println("El planeta ganador de la ronda " + contador_ronda + " es: " + ganador);

	}

	// funcion para mostrar el final de la partida
	// solo la utilizamos cuando un planeta muere

	public String mostrarFinalPartida() {
        String result = "" ;
		if (this.getNumVivos() == 0) {
			result += "<br><hr><p> Ha ocurrido una catastrofe interplanetaria y no ha quedad ninguno </p>";
		}else {
			puntero = 0;
			ganador = getNextVivo();
			result += "<br><hr><p> El ganador de la partida ha sido el planeta "+ganador.getNombre()+"</p>"
					+ "<p> con "+ganador.getVida() + " de vida y " + ganador.getMisiles_ataque() 
					+ "<br> misiles de ataque y "+ ganador.getMisiles_defensa() + " misiles de defensa. </p>"; 
		}
        
		result += "<br><p> Se ha completado "+contador_ronda+ " rondas. </p>"; 
		return result;
	}
	
}
