import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	private ArrayList<Account> accounts;
	private ArrayList<Community> communities;
	private Account userAccount;
	
	AccountManager(Account userAccount) {
		this.accounts = new ArrayList<Account>();
		this.communities = new ArrayList<Community>();
		this.userAccount = userAccount;
	}
	
	//Create account
	public void createAccount() {
		Scanner userInput = new Scanner(System.in);
		//userAccount = new Account();
		
		//Login
		System.out.println("Inform your login:");
		userAccount.setAccountLogin(userInput.nextLine());
		
		//Password
		System.out.println("Inform your password:");
		userAccount.setAccountLogin(userInput.nextLine());
		
		if(!accounts.contains(userAccount)) {
			accounts.add(userAccount);
		}
		else {
			System.out.println("User already exists");
		}
		userInput.close();
	}//End method createAccount
	
	//Remove account
	public void removeAccount() {
		accounts.remove(userAccount);
		userAccount = null;
	}//End method removeAccount
	
	//Add friend
	public void addFriend(String friendName) {
		userAccount.addFriend(friendName);
		int pos = accounts.indexOf(userAccount);
		//Refreshing user account at accounts database
		accounts.set(pos, userAccount);
	}//End method addFriend
	
	//Send a message to another user
	public void sendMessage(){
		Scanner userInput = new Scanner(System.in);
		String sender = userAccount.getProfileName();
		
		System.out.println("Receiver:");
		String receiver = userInput.nextLine();
		
		System.out.println("Message:");
		String text = userInput.nextLine();
		text = text + "\n\nFrom: " + sender;
		
		Message message = new Message(sender, receiver, text);
		
		for(int acnts = 0; acnts < accounts.size(); acnts++) {
			if(accounts.get(acnts).getProfileName() == receiver) {
				Account tempAccount = accounts.get(acnts);
				tempAccount.newMessage(message);
				accounts.set(acnts, tempAccount);
				break;
			}
		}
		userInput.close();
	}//End method sendMessage
}//End class AccountMenager