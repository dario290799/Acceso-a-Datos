package coldwar.utildb;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;


import coldwar.Partida;
import coldwar.Planeta; 


public class DatosBDD {
	
	private static final String USER = "DAM1_48055765W";
	private static final String PWD = "A48055765W";
	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	private static Connection conectarBaseDatos() {
		Connection con = null;

		System.out.println("Intentando conectarse a la base de datos");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

		System.out.println("Conectados a la base de datos");

		return con;
	}

	public static Partida cargarPartida() {
		Connection con = conectarBaseDatos();
		String sql = "SELECT p.* FROM coldwar_partida p";
        Partida partida = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int num_rondas = rs.getInt("num_ronda");
					int puntero = rs.getInt("puntero");
					String hora = rs.getString("puntero");
					String fecha  = rs.getString("puntero");

				    partida = new Partida();
				    partida.setPuntero(puntero);
				    partida.setContador_ronda(num_rondas);
					System.out.println("Partida creada el "+ fecha + " "+ hora+" cargada con exito");

				}
			} else {
				System.out.println("No he encontrado nada");
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cargarJugadores(con, partida);
		
		return partida;
	}
	
	public static void cargarJugadores(Connection con, Partida partida) {
	   String sql = "SELECT p.* FROM coldwar_planeta p";
       try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					int vidas = rs.getInt("vidas");
					int misiles_ataque = rs.getInt("misiles_ataque");
					int misiles_defensa = rs.getInt("misiles_defensa");
					int misiles_ronda = rs.getInt("misiles_ronda");
					String nombre = rs.getString("nombre");
					String tipo  = rs.getString("tipo");
                    
					Planeta p = new Planeta(nombre, tipo);
					p.setVida(vidas);
					p.setMisiles_ataque(misiles_ataque);
					p.setMisiles_defensa(misiles_defensa);
					p.setMisiles_ronda(misiles_ronda);
					
				    partida.add_equipo(p);

				}
			} else {
				System.out.println("No he encontrado nada");
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void borrarDatos(Connection con) {
		String sql = "delete from coldwar_partida";
		String sql1 = "delete from coldwar_planeta";
		
	
	
			try {
				Statement st = con.createStatement();
				st.execute(sql);
				
				System.out.println("Se ha guardado la partida correctamente.");
			} catch (SQLException e) {
				System.out.println("Ha habido un error en el Insert " + e);
			}

	}

	public static void guardarPartida( Partida p) {
		Connection con = conectarBaseDatos();
		borrarDatos(con);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
	    String fecha = format.format(date);
	    format = new SimpleDateFormat("HH:mm");
	    String hora = format.format(date);
	    
		String sql = "INSERT INTO coldwar_partida VALUES(1, '" + fecha + "', '"+
				       hora+ "', " + p.getContador_ronda() + ", "+ p.getPuntero()+")";
		
		
		try {
			Statement st = con.createStatement();
			st.execute(sql);
			
			System.out.println("Se ha guardado la partida correctamente.");
		} catch (SQLException e) {
			System.out.println("Ha habido un error en el Insert " + e);
		}
		
		Planeta[] planetas = p.getPlanetas(); 
		for (int i = 0; i< planetas.length; i++) {
			sql = "INSERT INTO coldwar_planeta VALUES("+i+ ", " + planetas[i].getVida() + ", '"+
					planetas[i].getTipo_planeta()+ "', '" + planetas[i].getNombre() + "', "
					+ planetas[i].getMisiles_ataque()+ ", "+ planetas[i].getMisiles_defensa()+ ", "+
					planetas[i].getMisiles_ronda()+")";
			
			try {
				Statement st = con.createStatement();
				st.execute(sql);
				
				System.out.println("Se ha guardado el planeta "+ planetas[i].getNombre()+" correctamente");
			} catch (SQLException e) {
				System.out.println("Ha habido un error en el Insert " + e);
		}
		}
	
	}
}
