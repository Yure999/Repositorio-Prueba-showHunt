import java.util.Scanner;

public class TestConexion {

	public static void main(String[] args) {

		Conexion conexion = new Conexion();
		Scanner sc1 = new Scanner(System.in);
		while(true) {
			System.out.println("1) See shows");
			System.out.println("2) See users");
			System.out.println("3) See bands");
			int selector = sc1.nextInt();
			switch (selector) {
			case 1:
				conexion.getShows(conexion);
				break;
			case 2:
				conexion.getUsers(conexion);
				break;
			case 3:
				conexion.getBands(conexion);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + selector);
			}
		}
	}
}