package please;

import java.sql.*;
import java.util.Scanner;

public class UserCreator {
	// JFrame frame = new JFrame("JOptionPane showMessageDialog example");
//	JOptionPane.showMessageDialog(frame, "Cancer");

	static String dbc = "jdbc:mysql://localhost:3306/transactions";
	static String dbc_user = "root";
	static String dbc_password = "";

	static Scanner scanner = new Scanner(System.in);
	static String CreateUserQuery = "INSERT INTO test VALUES (default,'%s', '%s','%s','%s','%s')";
	static String DeleteUserQuery = "DELETE FROM test WHERE  BadgeID='%s';";
	static String SelectAllFromTest = "SELECT * from test";
	static String SelectAllFromOffices = "SELECT * from offices";

	public static Boolean ExistsInDB(String Query, String DataBaseField, String Field) {
		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery(Query);

			while (myRs.next()) {
				if ((myRs.getString(DataBaseField).equals(Field))) {
					return true;
				}
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		Selector();
	}

	public static void Selector() {
		String var = userInput("Do you wish to 'create' or 'delete' user?");

		if (var.contains("create")) {
			CreateUser();
		} else if (var.contains("delete")) {
			DeleteUser();
		} else {
			Selector();
		}
	}

	public static void DeleteUser() {
		String ID = userInput("Enter existing user Badge ID: ");

		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);

			Statement myStat = myConn.createStatement();

			if (myStat.executeUpdate(String.format(DeleteUserQuery, ID)) == 1) {
				println("Successfuly Deleted User with Badge ID " + "(" + ID + ")");
				Selector();
			} else {
				println(String.format("Couldnt find user with Badge ID(%s)", ID));
				DeleteUser();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public static void CreateUser() {
		try {
			Connection myConn = DriverManager.getConnection(dbc, dbc_user, dbc_password);
			Statement myStat = myConn.createStatement();
			ResultSet myRs = myStat.executeQuery(SelectAllFromTest);

			while (myRs.next()) {
				String ID = userInput("Enter new user Badge ID: ");
				if (ExistsInDB(SelectAllFromTest, "BadgeID", ID)) {
					println("Badge ID already exists! Please try a new Badge ID instead");
					CreateUser();

				} else {
					String Office = userInput("Enter your Office ID: ");
					if (ExistsInDB(SelectAllFromOffices, "ID", Office)) {
						String[] Name = userInput("Enter new user First and Last name: ").split(" ");
						String Password = userInput("Enter new user Password: ");

						if (Name.length > 1) {
							if (myStat.executeUpdate(String.format(CreateUserQuery, ID, Office, Name[0],
									Name[Name.length - 1], Password)) == 1) {
								println("Successfuly Created User '" + Name[0] + " " + Name[Name.length - 1] + "' ("
										+ ID + ")");
								Selector();
							}
						} else {
							if (myStat.executeUpdate(
									String.format(CreateUserQuery, ID, Office, Name[0], " ", Password)) == 1) {
								println("Successfuly Created User '" + Name[0] + "' (" + ID + ")");
								Selector();
							}
						}
						println("Failed to create new user! Please retry");
						CreateUser();
					} else {
						println("Office ID doesnt exist!");
						CreateUser();
					}
				}
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