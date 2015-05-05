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
	
	public ArrayList<String> getAccountsList() {
		return this.accountList;
	}//End method getAccountsList
	
	//Load iResearchAccounts
	public void loadAccounts() throws IOException  {
		try {
			bufferedReader = new BufferedReader(
					new FileReader("accounts/accounstList.txt"));
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
				new FileWriter("accounts/accounstList.txt"));
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
		
		//Login
		System.out.println("Inform your login:");
		userAcc.setLogin(userInput.nextLine());
		
		//Password
		System.out.println("Inform your password:");
		userAcc.setPassword(userInput.nextLine());
		
		//ProfileName
		System.out.println("Inform your profile name:");
		userAcc.setProfileName(userInput.nextLine());
		
		//Create account file
		if(!accountList.contains(userAcc.getLogin())) {
			BufferedWriter bufferedWriter = null;
			try {
				bufferedWriter =
						new BufferedWriter(
				new FileWriter(userAcc.getLogin()+".txt"));
				bufferedWriter.write("Login:"+userAcc.getLogin());
				bufferedWriter.write('\n');
				bufferedWriter.write("Password:"+userAcc.getPassword());
				bufferedWriter.write('\n');
				bufferedWriter.write("Login:"+userAcc.getProfileName());
				bufferedWriter.write('\n');
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
		//Delete account file
		File temp =
				new File("accounts/"+userAcc.getLogin()+".txt");
		temp.delete();
		accountList.remove(userAcc.getLogin());
		
		//Refresh accountList file
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter =
					new BufferedWriter(
			new FileWriter("accounts/accounstList.txt"));
			for(int i = 0; i < accountList.size(); i++) {
				bufferedWriter.write(accountList.get(i));
			}
		}
		catch(FileNotFoundException e1) {
			System.out.println("User doesn't exist");
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
		finally {
			bufferedWriter.close();
		}
	}//End method removeAccount
	
	//Send a message to another user
	public void sendMessage(Account userAcc) throws IOException{
		Scanner userInput = new Scanner(System.in);
		String sender = userAcc.getProfileName();
		
		System.out.println("Receiver:");
		String receiver = userInput.nextLine();
		
		System.out.println("Message:");
		String text = userInput.nextLine();
		text = text + "**From:" + sender;
		
		BufferedWriter bufferedWriter = null;
		try {			
			bufferedWriter = new BufferedWriter(
					new FileWriter("accounts/"+receiver+".txt", true));
			bufferedWriter.write(text);
		}
		catch(FileNotFoundException e1) {
			System.out.println("User Doesn't exist");
		}
		finally {
			bufferedWriter.close();
		}
		userInput.close();
	}//End method sendMessage
	
	//Send a friend request to another user
	public void friendRequest(Account userAcc)
			throws IOException {
		Scanner userInput = new Scanner(System.in);
		String sender = userAcc.getProfileName();
		
		System.out.println("Friend name:");
		String receiver = userInput.nextLine();
	
		//001 = code for friend request
		String text = "001" + sender;
		
		BufferedWriter bufferedWriter = null;
		try {			
			bufferedWriter = new BufferedWriter(
					new FileWriter("accounts/"+receiver+".txt", true));
			bufferedWriter.write(text);
			bufferedWriter.write('\n');
		}
		catch(FileNotFoundException e1) {
			System.out.println("User Doesn't exist");
		}
		finally {
			bufferedWriter.close();
		}
		userInput.close();
	}//End method sendFriendRequest
	
	public boolean validateAccount(String login, String password)
			throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader =
					new BufferedReader(
			new FileReader("accounts/"+login+".txt"));
			String line;
			for(int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				if(i == 1) {
					if(password == line) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		catch(FileNotFoundException e1) {
			System.out.println("User doesn't exist");
		}
		catch(IOException e2) {
			e2.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
		return false;
	}//End method validadeAccount
}//End class AccountMenager