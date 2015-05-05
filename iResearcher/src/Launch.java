import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Launch {
	public static void main(String[] args) {
		AccountManager manager;
		Account userAccount = new Account();
		ArrayList<Account> accounts;
		
		Scanner userInput = new Scanner(System.in);
		
		while(true) {
			System.out.println("Press C to creat a new a account");
			System.out.println("Press L to login");
			String ch = userInput.nextLine();
			
			if(ch.equals("C")) {
				manager = new AccountManager();
				try {
					manager.createAccount(userAccount);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//break;
			}
			
			else if(ch.equals("L")) {
				//break;
			}
		}
	}
}