package please;
import java.sql.*;
import java.util.Scanner;

public class UserCreator {
	static String dbc = "jdbc:mysql://localhost:3306/transactions";
	static String dbc_user = "root";
	static String dbc_password = "";
	
	static Timestamp date;
	static java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Scanner scanner = new Scanner(System.in);
	static String CreateUserQuery = "INSERT INTO temployed VALUES (default,'%s','%s','%s','%s','%s',default,default)";

	public static void main(String[] args) {
		String[] Name = userInput("Enter new user First and Last name: ").split(" ");
		String Password = userInput("Enter new user Password: ");
		String Rank = userInput("Enter new user Rank: ");
		String CI = userInput("Enter new user CI: ");
		
		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);

			Statement myStat = myConn.createStatement();

			if (myStat.executeUpdate(String.format(CreateUserQuery, Rank, Name[0], Name[1], Password, CI)) == 1) {
				println("Successfuly Created User '" + Name[0] +  " " + Name[1] + "' (" + CI + ")");
			}
			else
			{
				println("Failed Query (check 'UserCreator.java')");
				// Debug failsafe?
			}

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}
	
	public static String userInput(String statement) {
		System.out.println(statement);
		return scanner.nextLine();
	}
	public static void println(String line) {
		System.out.println(line);
	}
}