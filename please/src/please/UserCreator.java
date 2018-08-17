package please;

import java.awt.event.KeyListener;
import java.io.InputStream;
import java.sql.*;
import java.util.Scanner;

public class UserCreator {
	static Timestamp date;
	static java.text.SimpleDateFormat formatdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter new UserName:");
		String Username = scanner.nextLine();
		System.out.print("Enter password:");
		String Password = scanner.nextLine();
		
		
		int i = 0;
		
		// ADDED COOL LCOMMENT TO TEST GIT
		
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transactions", "root", "");

			Statement myStat = myConn.createStatement();

			ResultSet myRs = myStat.executeQuery("select * from temployed");

			while (myRs.next()) {
				if ((myRs.getString("CI")).equals(CIP)) {
					
					date = myRs.getTimestamp("Date");
					
					System.out.println();
					double enterhour = myRs.getDouble("EnteringHour");
					double exithour = myRs.getDouble("ExitHour");
					double workingtime = exithour - enterhour;
					System.out.println(myRs.getString("LastName") + " works " + workingtime + "Hours");
					i++;
				}
			
			}
			if (i == 0)
				System.out.println("That CI is not in the DATABASE");

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}
}