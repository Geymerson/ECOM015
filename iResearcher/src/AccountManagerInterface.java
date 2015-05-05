import java.io.IOException;
import java.util.ArrayList;

public interface AccountManagerInterface {
	public ArrayList<String> getAccountsList();

	//Load iResearchAccounts
	public void loadAccounts() throws IOException;
	
	//Save iResearchAccount
	public void saveAccount() throws IOException;

	//Create account
	public void createAccount(Account userAcc) throws IOException;

	//Remove account
	public void removeAccount(Account userAcc) throws IOException;

	//Send a message to another user
	public void sendMessage(Account userAcc) throws IOException;
	
	//Send a friend request to another user
	public void friendRequest(Account userAcc) throws IOException;

	//Check the existence of an account
	public boolean validateAccount(String login, String password) throws IOException;
}
