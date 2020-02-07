
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Conexion {
	private static String USUARIO = "usuario";
	private static String PASS = "12345";
	private static final String BBDD = "showHuntDB";
	private static final String URL = "jdbc:mysql://localhost:3306/" + BBDD;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String user;

	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			System.out.println("Error al cargar el driver");
			e.printStackTrace();
		}
	}

	/**
	 * metodo que establece la conexion con la base de datos se pasa como parametro
	 * el usuario de la base de datos, en el caso de que el usuario sea root se
	 * quita la password
	 * 
	 * @return
	 */
	public Connection conectar(String conexionUser) {
		Connection conexion = null;
		if (this.USUARIO.equals("root")) {
			this.PASS = "";
		}
		System.out.println(URL + conexionUser + PASS);
		try {
			conexion = DriverManager.getConnection(URL, conexionUser, PASS);
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}

	/**
	 * metodo que utiliza el valor del atributo user de conexion para sacar el ID de
	 * ese usuario
	 * 
	 * @param conexion
	 * @param userName
	 * @return
	 */
	public int getUserID(Conexion conexion, String userName) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace query
		ResultSet rs = null;
		int userID = 0;

		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			String selectQuery = "select id_usuario from usuarios where nombreUsuario = '" + this.user + "';";
			rs = stm.executeQuery(selectQuery);
			userID = rs.getInt("id_usuario");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return userID;
	}

	/**
	 * Metodo que lista todos los usuarios de la base de datos solo los
	 * administradores pueden usarlo
	 * 
	 * @param conexion
	 */
	public void getUsers(Conexion conexion) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys
		if (this.USUARIO.equals("root")) {
			try {
				cn = conexion.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
				stm = cn.createStatement();// inicia las cosas
				rs = stm.executeQuery("select * from usuarios; ");

				while (rs.next()) {
					int idUser = rs.getInt("id_usuario");
					String userName = rs.getString("nombreUsuario");
					String userPass = rs.getString("passwordUsuario");
					String userMail = rs.getString("correoUsuario");
					String userLocation = rs.getString("ciudadUsuario");

					System.out.println("USERS INFO: ID: " + idUser + " User name: " + userName + " User password: "
							+ userPass + " User mail: " + userMail + " User location: " + userLocation);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
		} else {
			System.out.println("permiso denegado");
		}
	}

	/**
	 * Metodo que lista todos los conciertos de la base de datos solo los
	 * administradores pueden usarlo
	 * 
	 * @param conexion
	 */
	public void getShows(Conexion conexion) {

		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys

		if (this.USUARIO.equals("root")) {
			try {
				cn = conexion.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
				stm = cn.createStatement();// inicia las cosas
				rs = stm.executeQuery("select * from conciertos; ");

				while (rs.next()) {
					int idShow = rs.getInt("id_concierto");
					int idBand = rs.getInt("id_grupo");
					String city = rs.getString("ciudad");
					String location = rs.getString("lugar");
					String ticketsLink = rs.getString("linkEntradas");

					System.out.println("SHOWS INFO: Show ID: " + idShow + " Band ID: " + idBand + " Show city: " + city
							+ " Show location: " + location + " Tickets link: " + ticketsLink);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
		} else {
			System.out.println("permiso denegado");
		}
	}

	/**
	 * Metodo que lista todos los grupos de la base de datos, solo los
	 * administradores pueden usarlo
	 * 
	 * @param conexion
	 */
	public void getBands(Conexion conexion) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys

		if (this.USUARIO.equals("root")) {
			try {
				cn = conexion.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
				stm = cn.createStatement();// inicia las cosas
				rs = stm.executeQuery("select * from grupos; ");

				while (rs.next()) {
					int bandID = rs.getInt("id_grupo");
					String bandName = rs.getString("nombreGrupo");
					String bandImgLink = rs.getString("imagenGrupo");

					System.out.println(
							"BANDS INFO: ID: " + bandID + " Band name: " + bandName + " Image link: " + bandImgLink);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
		} else {
			System.out.println("permiso denegado");
		}
	}

	/**
	 * metodo que muestra las recomendaciones de un usuario, primero llama al metodo
	 * getUserID, luego muestra todos los conciertos de las tablas favoritos y del
	 * historial asociados a ese ID de usuario
	 * 
	 * @param conexion
	 */
	public void showRecomended(Conexion conexion) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys

		try {
			int userID = conexion.getUserID(conexion, this.user);
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			String query = "select distinct * from conciertos\r\n"
					+ "inner join favoritos f on conciertos.id_grupo = f.id_grupo\r\n"
					+ "inner join historial h on conciertos.id_grupo = h.id_grupo\r\n"
					+ "where (conciertos.id_grupo = f.id_grupo or conciertos.id_grupo = h.id_grupo) and id_usuario = '"
					+ userID + "';";

			rs = stm.executeQuery(query);
			while (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * Metodo para registrar usuarios, el Id de usuario se autoincrementa, por
	 * defecto el nuevo usuario tendra el rol de user
	 * 
	 * @param conexion
	 * @param userName
	 * @param userPass
	 * @param userMail
	 * @param userCity
	 */
	public void registerUser(Conexion conexion, String userName, String userPass, String userMail, String userCity) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		System.out.println("hola");
		try {
			cn = conexion.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
			stm = cn.createStatement();// inicia las cosas
			String query = "insert into usuarios(nombreUsuario, passwordUsuario, correoUsuario, ciudadUsuario) values('"
					+ userName + "','" + userPass + "','" + userMail + "','" + userCity + "');";
			stm.executeUpdate(query);
			System.out.println("entra");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

	/**
	 * Metodo para el login de usuarios, hace una query con el nombre de usuario y
	 * la password que el usuario haya introducido si encuentra un registro con esos
	 * dos valores devuelve true y el nombre de usuario da valor al atributo user de
	 * la clase conexion, si no se encuentra ningun registro devuelve false, si el
	 * usuario que hace login es un administrador en las siguientes llamadas al
	 * metodo conexion se pasara root como parametro usuario u
	 * 
	 * @param conexion
	 * @param userName
	 * @param userPass
	 * @return
	 */
	public boolean loginUser(Conexion conexion, String userName, String userPass) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys
		ResultSet rs = null;// las querys

		try {
			cn = conexion.conectar(this.USUARIO);// llama al metodo conectar de la clase conexion
			stm = cn.createStatement();// inicia las cosas
			String query = "select nombreUsuario, passwordUsuario, administrador from usuarios where nombreUsuario ='"
					+ userName + "' and passwordUsuario ='" + userPass + "';";
			rs = stm.executeQuery(query);

			if (rs.next()) {
				System.out.println("Login correcto");
				int administrador = rs.getInt("administrador");
				if (administrador == 1) {
					this.USUARIO = "root";
					conexion.conectar(this.USUARIO);
					System.out.println("conectado como root");
				} else {
					System.out.println("conectado como usuario");
				}
				this.user = userName;
				return true;
			} else {
				System.out.println("Login incorrecto");
				return false;
			}

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		} finally {
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

	/**
	 * Metodo que elimina a un usuario de la base de datos, puede ser usado por
	 * administradores para borrar cualquier usuario o por usuarios, en este caso el
	 * usuario solo puede eliminarse de la base de datos a si mismo y cuando lo haga
	 * se cerrara su sesion
	 * 
	 * @param conexion
	 * @param userName
	 */
	public void removeUser(Conexion conexion, String userName) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace querys

		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			String query = "delete from usuarios where nombreUsuario = '" + userName + "';";
			stm.executeUpdate(query);
			System.out.println("ususario " + userName + " borrado");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
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

	/**
	 * Metodo de busqueda filtrando por el nombre de la banda, si se encuentran
	 * registros el metodo los devuelve todos, indicando la ciudad, el lugar, la
	 * fecha y un link para comprar las entradas, ademas saca el ID del grupo para
	 * pasarlo a la tabla historial asociado con el usuario que realizo la busqueda,
	 * en el caso de que no encuentre registros se lo indicara al usuario
	 * 
	 * @param conexion
	 * @param searchedBandName
	 */
	public void searchByBand(Conexion conexion, String searchedBandName) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace query
		ResultSet rs = null;// las querys
		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			String selectQuery = "select id_grupo, nombreGrupo, ciudad, lugar,fecha,linkEntradas from conciertos\r\n"
					+ "    inner join grupos g on conciertos.id_grupo = g.id_grupo\r\n" + "    where nombreGrupo = '"
					+ searchedBandName + "';";
			rs = stm.executeQuery(selectQuery);

			if (rs.next()) {
				while (rs.next()) {
					String bandName = rs.getString("nombreGrupo");
					String showCity = rs.getString("ciudad");
					String showPlace = rs.getString("lugar");
					String showDate = rs.getDate("fecha") + " ";
					String ticketsLink = rs.getString("linkEntradas");
					int bandID = rs.getInt("id_grupo");

					System.out.println("Resltados de buscar " + bandName + " : Hay conciertos de ese grupo en: "
							+ showCity + " en: " + showPlace + " el dia " + showDate + " link de compra de entradas: "
							+ ticketsLink);

					String insertQuery = "insert into historial(id_usuario, id_grupo)\r\n" + "values('" + this.user
							+ "','" + bandID + "');";
					stm.executeUpdate(insertQuery);
				}
			} else {
				System.out.println("No hay registros relacionados con el criterio de busqueda");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * Metodo de busqueda filtrando por ciudad, en el caso de encotrar registros los
	 * devuelve todos indicando el nombre del grupo, el lugar del concierto y la
	 * fecha, este metodo no guarda datos en la tabla historial, si no se encuentran
	 * registros se lo indicara al usuario
	 * 
	 * @param conexion
	 * @param city
	 */
	public void searchByCity(Conexion conexion, String city) {

		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace query
		ResultSet rs = null;// las querys
		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			String query = "select nombreGrupo, ciudad, lugar,fecha,linkEntradas from conciertos\r\n"
					+ "    inner join grupos g on conciertos.id_grupo = g.id_grupo\r\n" + "    where ciudad  = '" + city
					+ "';";
			rs = stm.executeQuery(query);

			if (rs.next()) {
				while (rs.next()) {
					String bandName = rs.getString("nombreGrupo");
					String showCity = rs.getString("ciudad");
					String showPlace = rs.getString("lugar");
					String showDate = rs.getDate("fecha") + " ";
					String ticketsLink = rs.getString("linkEntradas");

					System.out.println("Resltados de buscar " + showCity + " : Hay conciertos en esa ciudad de: "
							+ bandName + " en: " + showPlace + " el dia " + showDate + " link de compra de entradas: "
							+ ticketsLink);
				}
			} else {
				System.out.println("No hay registros relacionados con el criterio de busqueda");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * Metodo que busca en la base de datos los conciertos comprendidos entre la
	 * fecha en la que se ejecuta el metodo y el espacio de tiempo seleccionado por
	 * el usuario, la query devulve todos los registros que cumplen las condiciones
	 * y si ninguno lo cumple se lo comunica al usuario
	 * 
	 * @param conexion
	 * @param time
	 */
	public void searchByDate(Conexion conexion, int time) {
		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace query
		ResultSet rs = null;// las querys

		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			String stringDate = sdf.format(date.getTime());

			date.add(Calendar.DAY_OF_MONTH, time);
			String newStringDate = sdf.format(date.getTime());

			String query = "select nombreGrupo,ciudad,lugar,fecha,linkEntradas from conciertos\r\n" + 
					"inner join grupos g on conciertos.id_grupo = g.id_grupo\r\n" + 
					"where fecha > '"+stringDate+"' and fecha < '"+newStringDate+"';";
			System.out.println(query);
			rs = stm.executeQuery(query);

			if (rs.next()) {
				while (rs.next()) {
					String bandName = rs.getString("nombreGrupo");
					String showCity = rs.getString("ciudad");
					String showPlace = rs.getString("lugar");
					String showDate = rs.getDate("fecha") + " ";
					String ticketsLink = rs.getString("linkEntradas");

					System.out.println("Conciertos entre las fechas " + stringDate + " y " + newStringDate + " Grupo "
							+ bandName + " ciudad: " + showCity + " lugar: " + showPlace
							+ " link de compra de entradas: " + ticketsLink);
				}
			} else {
				System.out.println("No hay registros relacionados con el criterio de busqueda");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	/**
	 * Metodo para meter bandas en favoritos, primero llama al metodo getUserID para
	 * sacar el id asociado al valor del parametro user de conexion, luego utiliza
	 * ese ID y el ID del grupo que se vaya a meter en favoritos
	 * 
	 * @param conexion
	 * @param bandID
	 */
	public void addToFavorites(Conexion conexion, int bandID) {

		Connection cn = null;// conexion
		Statement stm = null;// cosa que hace query
		ResultSet rs = null;

		try {
			cn = conexion.conectar(this.USUARIO);
			stm = cn.createStatement();
			int userID = conexion.getUserID(conexion, this.user);

			String insertQuery = "insert into favoritos(id_ususario,id_grupo)\r\n" + "    values(" + userID + ","
					+ bandID + ");";
			stm.executeUpdate(insertQuery);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

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