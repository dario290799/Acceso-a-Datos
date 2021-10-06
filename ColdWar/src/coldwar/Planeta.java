package coldwar;


public class Planeta {
	

	public static int NUM_EQUIPOS = 0;
	
	public static String NORMAL = "Normal";
	public static String ROJO = "Rojo";
	public static String AZUL = "Azul";
	public static String VERDE = "Verde";
	public static String GASEOSO = "Gaseoso";
	public static String ENANO = "Enano";
	public static String GIGANTE = "Gigante";
	public static String BRILLANTE = "Brillante";
	public static String ESCURRIDIZO = "Escurridizo";
	public static String FIESTERO = "Fiestero";

    //Atributos del planeta.
	private int vida = 200;
	private String nombre;
	private int misiles_ronda = 50;
	private int misiles_ataque = 0;
	private String tipo_planeta = NORMAL;
	private int misiles_defensa = 0;
	
	
	public int getMisiles_ataque() {
		return misiles_ataque;
	}

	public void setMisiles_ataque(int misiles_ataque) {
		this.misiles_ataque = misiles_ataque;
	}

	public int getMisiles_defensa() {
		return misiles_defensa;
	}

	
	// Metodos del palneta
	public Planeta(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo_planeta = tipo;
		iniciarPlaneta();
	}
	

	public int getVida() {
		return vida;
	}

	public void setVida(int value) {
		if (value <0)
		    this.vida = 0;
		else
			this.vida = value;
	}

	public int getMisiles_ronda() {
		return misiles_ronda;
	}

	public void setMisiles_ronda(int misiles_ronda) {
		this.misiles_ronda = misiles_ronda;
	}

	public String getTipo_planeta() {
		return tipo_planeta;
	}

	public void setTipo_planeta(String tipo_planeta) {
		this.tipo_planeta = tipo_planeta;
	}

	public void setMisiles_defensa(int misiles_defensa) {
		this.misiles_defensa = misiles_defensa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
    public boolean estaVivo() {
		
		return this.vida>0;
	}
    

	private void setMisiles_Defensa(int value) {
		if (value < 0 )
			this.misiles_defensa = 0;
		else
			this.misiles_defensa = value;
	}


	public void nuevaRonda(int ronda) {
		if (estaVivo()) {
			
			if (this.tipo_planeta.equals(GIGANTE)){
               this.misiles_ataque = misiles_ronda + (10*ronda);
               
			}else if (this.tipo_planeta.equals(FIESTERO)) {
				this.misiles_ataque = misiles_ronda + (30*ronda);
				setVida(vida - (100*ronda));
			}else if (this.tipo_planeta.equals(BRILLANTE)) {
				this.misiles_ataque = misiles_ronda + (30*ronda);
				setVida(vida - (100*ronda));
			}else {    
		     	this.misiles_ataque += misiles_ronda;
			}
			
		}
		
	}

	

	public String addDefensa(int misiles, Planeta p) {
		misiles = resolverDefensa(misiles, p);
		int defensa = misiles_defensa;
		if (defensa < misiles) {
			int ataque = misiles - defensa;
			setMisiles_Defensa(0);
			setVida(vida - ataque);

		} else if (defensa >= misiles) {
		     setMisiles_Defensa(misiles_defensa - misiles);

		}
		return "<hr><p>"+ this.getNombre() + " ha sido atacado por "+ p.getNombre()+ "</p>"
				+ "<p> se ha defendido con " + defensa + " misiles</p>" 
				+ "<p> su vida queda en  "+ getVida() +"</p>";
	}

	//Resolucion de la defensa segun el tipo de planeta.
	private int resolverDefensa(int misiles, Planeta p) {
		if (this.tipo_planeta.equals(ENANO)){
		    return (int)((double)misiles / (Math.random()*2)+1);
		}else {
			 return misiles;
		}
	}

	
	 
	public String addAtaque(int misiles, Planeta p) {
		String result = "";
		setMisiles_Defensa(this.misiles_defensa + (this.misiles_ataque - misiles));	
        setMisiles_ataque(0);
        
        result += "<p>"+this.getNombre()+" ataca al planeta "+ p.getNombre() +"</p>" 
                  +" con "+ misiles +" y cuenta con  "+ this.misiles_defensa+" misiles de defensa</p>";
        
        misiles = resolverAtaque(misiles, p);
        result += p.addDefensa(misiles, this);
    
        return result;
	}

    //Resolucion de ataque segun el tipo de planeta
	private int resolverAtaque(int misiles, Planeta p) {
		if (this.tipo_planeta.equals(ROJO) && p.tipo_planeta.equals(VERDE)){
		    return misiles*2;
		}else if (this.tipo_planeta.equals(ROJO) && p.tipo_planeta.equals(AZUL)){
		    return misiles/2;
		}else if (this.tipo_planeta.equals(AZUL) && p.tipo_planeta.equals(ROJO)){
		    return misiles*2;
		}else if (this.tipo_planeta.equals(AZUL) && p.tipo_planeta.equals(VERDE)){
		    return misiles/2;
		}else if (this.tipo_planeta.equals(VERDE) && p.tipo_planeta.equals(AZUL)){
		    return misiles*2;
		}else if (this.tipo_planeta.equals(VERDE) && p.tipo_planeta.equals(ROJO)){
		    return misiles/2;
		}else {
			 return misiles;
		}
	}

	@Override
	public String toString() {
		return "Nombre: " + this.getNombre() + "Vidas: " + this.getVida() + "Misiles: " + this.getMisiles_ataque();
	}

	public String showEstado() {
		return "<p>"+this.nombre + " -> " + this.getVida() + " vidas.</p>";
	}
	
	//INicializamos los planetas con sus habilidades.
	public void iniciarPlaneta() {
		if (this.tipo_planeta.equals(GASEOSO)){
		    setVida(400);
		    setMisiles_ataque(10);
		    misiles_ronda = 10;
		}else if (this.tipo_planeta.equals(BRILLANTE)){
		    setVida(500);
		    setMisiles_ataque(110);
		    misiles_ronda = 10;
		}else if (this.tipo_planeta.equals(FIESTERO)){
		    setVida(15000);
		    setMisiles_Defensa(300);
		    setMisiles_ataque(10);
		    misiles_ronda = 10;
		}else if (this.tipo_planeta.equals(ENANO)){
		    setVida(100);
		    setMisiles_ataque(50);
		    misiles_ronda = 50;
		}else {
			 setVida(200);
			 setMisiles_ataque(50);
			 misiles_ronda = 50;
		}
	}
	
}
