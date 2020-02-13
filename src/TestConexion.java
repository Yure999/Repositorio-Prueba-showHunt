import java.util.Scanner;

public class TestConexion {

	public static void main(String[] args) {
		
		System.out.println("MENU DEBUG");
		
		Conexion conexion = new Conexion();
		Scanner sc1 = new Scanner(System.in);
		String userName = "";
		
		System.out.println("LOGIN");
		System.out.println("User: ");
		userName = sc1.next();
		System.out.println("Password: ");
		String userPass = sc1.next();
		conexion.loginUser(userName, userPass);
		
		while (true) {
			System.out.println("1) See shows");
			System.out.println("2) See users");
			System.out.println("3) See bands");
			System.out.println("5) Remove user");
			System.out.println("6) search by band name");
			System.out.println("7) Search by city name");
			System.out.println("8) Search by date");
			System.out.println("9) Add to favourites");
			int selector = sc1.nextInt();
			switch (selector) {
			case 1:
				conexion.getShows();
				break;
			case 2:
				conexion.getUsers();
				break;
			case 3:
				conexion.getBands();
				break;
			case 5:
				System.out.println("user: ");
				userName = sc1.next();
				conexion.removeUser(userName);
				break;
			case 4:
				System.out.print("UserID: ");
				System.out.println(conexion.getUserID());
				break;
			case 6:
				System.out.println("Band: ");
				sc1.nextLine();
				String bandName = sc1.nextLine();
				System.out.println(bandName);
				conexion.searchByBand(bandName );
				break;
			case 7:
				System.out.println("city: ");
				String city = sc1.next();
				conexion.searchByCity(city);
				break;
			case 8:
				System.out.println("1) 15 dias");
				System.out.println("2) 1 mes");
				System.out.println("3) 2 meses");
				int dateOption = sc1.nextInt();
				int selectedTime = 0;
				if(dateOption == 1) {
					selectedTime = 15;
				}
				else if(dateOption == 2) {
					selectedTime = 30;
				}
				else if(dateOption == 3) {
					selectedTime = 60;
				}
				else {
					System.out.println("no toques los cojones y elije una de las tres opciones");
				}
				
				conexion.searchByDate(selectedTime);
				break;
			case 9:
				System.out.println("bandID: ");
				int bandID = sc1.nextInt();
				conexion.addToFavorites(bandID);
				break;
			case 10:
				System.out.println("close session");
				conexion.closeSession();
			default:
				throw new IllegalArgumentException("Unexpected value: " + selector);
			}
		}
	}
}