package please;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JTextField;

public class UserLogin {
	static String dbc = "jdbc:mysql://localhost:3306/transactions";
	static String dbc_user = "root";
	static String dbc_password = "";
	static String office = new String();
	static Scanner scanner = new Scanner(System.in);
	static int i = 0;

	public static void main(String Username, String Password) {
		/*
		 * String Username1 = userInput("Enter your Username: "); String
		 * Password = userInput("Enter your Password: ");
		 */

		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);

			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from test");
			while (myRs.next()) {
				if ((myRs.getString("Username")).equals(Username)) {
					if ((myRs.getString("Passwords")).equals(Password)) {
						println("Succefully Logged In");
						println("Your name is " + myRs.getString("Name") + " " + myRs.getString("LastName") + " ID: "
								+ myRs.getString("BadgeID"));
						office = myRs.getString("Office");
						println("Your working office is: " + office);
						println("People of that office are (your colleagues): ");
						i++;
					} else {
						println("Wrong password please try again");
						i++;
					}

				}

			}
			ResultSet myRs2 = myStat.executeQuery("select * from test");
			while (myRs2.next()) {
				if (myRs2.getString("Office").equals(office)) {
					println(myRs2.getString("Name") + " " + (myRs2.getString("LastName")));
				}
			}

			if (i == 0) {
				println("Your username was not found try to Register first");
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
