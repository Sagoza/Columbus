package please;

import java.sql.*;
import java.util.Scanner;

import com.mysql.jdbc.UpdatableResultSet;

public class UserCreator {
	static String dbc = "jdbc:mysql://localhost:3306/transactions";
	static String dbc_user = "root";
	static String dbc_password = "";

	static Scanner scanner = new Scanner(System.in);
	static String CreateUserQuery = "INSERT INTO test VALUES (default,'%s', '%s','%s','%s','%s', '%s')";

	public static void main() {
		String[] Name = userInput("Enter new user First and Last name: ").split(" ");
		String Username1 = userInput("Enter your username");
		String Password = userInput("Enter new user Password: ");
		String Offices = userInput("Enter your Office name: ");
		String ID = userInput("Enter new user ID: ");
		int User = 1;
		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);

			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery("select * from test");
			while (myRs.next()) {
				if ((myRs.getString("Username")).equals(Username1)) {
					User = 0;
					println("User Already provided please try a new username or Login instead");
					return;
				}
				if ((myRs.getString("BadgeID").equals(ID))) {

					/*
					 * String update = "update test" +
					 * "set Username = 'Username1'" + " where BadgeID = 'ID'";
					 * myStat.executeUpdate(update);
					 */
					User = 0;
					println("badge Already provided please try a new badge or Login instead");

				}

			}
			if (User == 1) {
				/*
				 * if ((myRs.getString("BadgeID").equals(ID))) { Se já houver
				 * BadgeID e não houver user, significa que a pessoa já está na
				 * database mas ainda não se registou logo so quro dar update
				 * nos valores }
				 */
				if (myStat.executeUpdate(
						String.format(CreateUserQuery, ID, Offices, Name[0], Name[1], Username1, Password)) == 1) {
					println("Successfuly Created User '" + Username1 + "' (" + ID + ")");
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void update(String BadgeID, String Username1) {
		String sql = "UPDATE test SET Username = ? , " + " BadgeID = ? ";

	}

	public static String userInput(String statement) {
		System.out.println(statement);
		return scanner.nextLine();
	}

	public static void println(String line) {
		System.out.println(line);
	}
}