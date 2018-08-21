package please;

import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String choose = userInput("Please Say if you want to LogIN or Register");
	/*	switch (choose) {
		case "LogIn":
			UserLogin.main(Username, Password);
			break;
		case "Register":
			UserCreator.main();
			break;
		default:
			System.out.println("Please choose LogIn or Register");
		}
	*/}

	public static String userInput(String statement) {
		System.out.println(statement);
		return scanner.nextLine();
	}
}
