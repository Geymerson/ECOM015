import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	ArrayList<String> accountList;
	BufferedReader bufferedReader;
	
	public AccountManager(){
		accountList = new ArrayList<String>();
		
		try {
			this.loadAccounts();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Load iResearchAccounts
	public void loadAccounts() throws IOException  {
		try {
			bufferedReader = new BufferedReader(
					new FileReader("programFiles/accounstList.txt"));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				accountList.add(line);
			}
		}
		catch(FileNotFoundException e1) {
			BufferedWriter bufferedWriter = null;
			try {
				bufferedWriter =
						new BufferedWriter(
				new FileWriter("programFiles/accounstList.txt"));
			}
			catch(IOException e2) {
				e2.printStackTrace();
			}
			finally {
				bufferedWriter.close();
			}
		}
		catch (IOException e3) {
			e3.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
	}//End method loadAccounts
	
	//Create account
	public void createAccount(Account userAcc) throws IOException {
		Scanner userInput = new Scanner(System.in);
		//userAccount = new Account();
		
		//Login
		System.out.println("Inform your login:");
		userAcc.setLogin(userInput.nextLine());
		
		//Password
		System.out.println("Inform your password:");
		userAcc.setPassword(userInput.nextLine());
		
		System.out.println("Inform your profile name:");
		userAcc.setProfileName(userInput.nextLine());
		
		if(!accountList.contains(userAcc.getProfileName())) {
			BufferedWriter bufferedWriter = null;
			try {
				bufferedWriter =
						new BufferedWriter(
				new FileWriter(userAcc.getProfileName()+".txt"));
				bufferedWriter.write("Login:"+userAcc.getLogin());
				bufferedWriter.write("Password:"+userAcc.getPassword());
			}
			catch(IOException e2) {
				e2.printStackTrace();
			}
			finally {
				bufferedWriter.close();
			}
		}
		else {
			System.out.println("User already exists");
		}
		userInput.close();
	}//End method createAccount
	
	//Remove account
	public void removeAccount(Account userAcc) throws IOException {
		File temp =
				new File("programFiles/"+userAcc.getProfileName()+"txt");
		temp.delete();
		accountList.remove(userAcc.getProfileName());
		
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter =
					new BufferedWriter(
			new FileWriter("programFiles/accounstList.txt"));
			for(int i = 0; i < accountList.size(); i++) {
				bufferedWriter.write(accountList.get(i));
				bufferedWriter.write('\n');
			}
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
		finally {
			bufferedWriter.close();
		}
	}//End method removeAccount
	
	//Add friend
	public void addFriend(Account userAccount, String friendName) {
		userAccount.addFriend(friendName);	
	}//End method addFriend
	
	//Send a message to another user
	public void sendMessage(Account userAcc){
		Scanner userInput = new Scanner(System.in);
		String sender = userAcc.getProfileName();
		
		System.out.println("Receiver:");
		String receiver = userInput.nextLine();
		
		System.out.println("Message:");
		String text = userInput.nextLine();
		text = text + "\n\nFrom: " + sender;
		
		Message message = new Message(sender, receiver, text);
		
//		for(int acnts = 0; acnts < accounts.size(); acnts++) {
//			if(accounts.get(acnts).getProfileName() == receiver) {
//				Account tempAccount = accounts.get(acnts);
//				tempAccount.newMessage(message);
//				accounts.set(acnts, tempAccount);
//				break;
//			}
//		}
		userInput.close();
	}//End method sendMessage
}//End class AccountMenager