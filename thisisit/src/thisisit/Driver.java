package thisisit;
import java.sql.*;

public class Driver {


		public static void main(String[] args) {
			try {
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transactions", "root", "");
				
				Statement myStat = myConn.createStatement();
				
				ResultSet myRs = myStat.executeQuery("select * from temployed");
				
				while (myRs.next()){
					System.out.println((myRs.getString("FirstName")));
				}
				
			}
			catch (Exception exc) {
				exc.printStackTrace();
				
			}
		}

	}
