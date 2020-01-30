import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion{
	private static final String USUARIO = "root";
	private static final String PASS = "";
	private static final String BBDD = "showHuntDB";
	private static final String URL = "jdbc:mysql://localhost:3306/"+BBDD;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(DRIVER);
		}
		catch(Exception e){
			System.out.println("Error al cargar el driver");
            e.printStackTrace();
		}
	}
	public Connection conectar() {
        Connection conexion = null;
        
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, PASS);
            System.out.println("Conexión OK");

        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        
        return conexion;
    }	
	

	public void getUsers(Conexion conexion) {//muestra todos los usuarios de la base de datos
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys
		
		try {
			cn = conexion.conectar();//llama al metodo conectar de la clase conexion
			stm = cn.createStatement();//inicia las cosas
			rs = stm.executeQuery("select * from usuarios; ");
			
			while(rs.next()) {
				int idUser = rs.getInt("id_usuario");
				String userName = rs.getString("nombreUsuario");
				String userPass = rs.getString("passwordUsuario");
				String userMail = rs.getString("correoUsuario");
				String userLocation = rs.getString("ciudadUsuario");
				
				System.out.println("USERS INFO: ID: " + idUser + " User name: "+ userName+" User password: " + userPass + " User mail: "+userMail+" User location: "+userLocation);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stm != null) {
					stm.close();
				}

				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void getShows(Conexion conexion) {//muestra todos los conciertos de la base de datos
		
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys
		try {
			cn = conexion.conectar();//llama al metodo conectar de la clase conexion
			stm = cn.createStatement();//inicia las cosas
			rs = stm.executeQuery("select * from conciertos; ");
			
			while(rs.next()) {
				int idShow = rs.getInt("id_concierto");
				int idBand = rs.getInt("id_grupo");
				String city = rs.getString("ciudad");
				String location = rs.getString("lugar");
				String ticketsLink = rs.getString("linkEntradas");
				
				System.out.println("SHOWS INFO: Show ID: " + idShow + " Band ID: "+ idBand+" Show city: " + city + " Show location: "+location+" Tickets link: "+ticketsLink);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stm != null) {
					stm.close();
				}

				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public void getBands(Conexion conexion) {//lista todos los grupos de la base de datos
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys
		
		try {
			cn = conexion.conectar();//llama al metodo conectar de la clase conexion
			stm = cn.createStatement();//inicia las cosas
			rs = stm.executeQuery("select * from grupos; ");
			
			while(rs.next()) {
				int bandID = rs.getInt("id_grupo");
				String bandName = rs.getString("nombreGrupo");
				String bandImgLink = rs.getString("imagenGrupo");
				
				System.out.println("BANDS INFO: ID: "+bandID+" Band name: "+bandName+" Image link: "+bandImgLink);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stm != null) {
					stm.close();
				}

				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
