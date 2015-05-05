import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Launch {
	public static void main(String[] args) {
		AccountManager manager = new AccountManager();
		Account userAccount = new Account();
		ArrayList<String> accounts =  manager.getAccountsList();
		
		Scanner userInput = new Scanner(System.in);
		
		while(true) {
			System.out.println("Press C to creat a new a account");
			System.out.println("Press L to login");
			String ch = userInput.nextLine();
			
			if(ch.equals("C")) {
				try {
					manager.createAccount(userAccount);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			else if(ch.equals("L")) {
				System.out.println("Login:");
				String login = userInput.nextLine();
				System.out.println("Password");
				String password = userInput.nextLine();

				try {
					if(manager.validateAccount(login, password)) {
						userAccount.editProfile();
					}
					else {
						System.out.println("Wrong login or password!");
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}