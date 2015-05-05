import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	//Account Personal information
	private String accountLogin;
	private String accountPassword;
	private AccountManager manager = new AccountManager();
	
	//Profile
	private String profileName;
	private ArrayList<String> friendList;
	private ArrayList<Community> createdCommunities;
	
	public void createProfile(String profileName) {
		if(profileName != "") {
			this.profileName = profileName;
			friendList = new ArrayList<String>();
			createdCommunities = new ArrayList<Community>();
		}
	}//End method createProfile
	
	public void setLogin(String accountLogin) {
		if(accountLogin != "") {
			this.accountLogin = accountLogin;
		}
	}//End method setAccountLogin
	
	public String getLogin(){
		return this.accountLogin;
	}//End method getAccountLogin
	
	public void setPassword(String password) {
		if(password != "") {
			this.accountPassword = password;
		}
	}//End method setPassword
	
	public String getPassword() {
		return this.accountPassword;
	}//End method getPassword
	
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}//End method setProfileName
	
	public String getProfileName() {
		return this.profileName;
	}//End method getProfileName
	
	public void addFriend(String friendName) {
		friendList.add(friendName);
	}//End method addFriend
	
	public void clearFriendList() {
		friendList.clear();
	}//End method clearFriendList
	
	public void editProfile() throws IOException {
		Scanner userInput = new Scanner(System.in);
		System.out.println("User profile editing options:");
		System.out.println("Change profile name 1");
		System.out.println("Change password 2");
		System.out.println("Create community 3");
		System.out.println("Send a message 4");
		System.out.println("Send friend request 5");
		System.out.println("Manage communities 6");
		System.out.println("Logout 7");
		
		int option = userInput.nextInt(); 
		
		switch(option){
			case 1:
				System.out.println("New profile name:");
				this.setProfileName(userInput.nextLine());
				break;
			case 2:
				System.out.println("New password:");
				this.setPassword(userInput.nextLine());
				break;
			case 3:
				System.out.println("Community Name:");
				String cmtName = userInput.nextLine();
				System.out.println("Description:");
				String desc = userInput.nextLine();
				this.createCommunity(cmtName, desc);
				break;
			case 4:
				manager.sendMessage(this);
				break;
			case 5:
				manager.friendRequest(this);
				break;
			case 6:
				break;
			default:
				break;
		}
	}
	
	public void createCommunity(String comunityName, String desc) {
		Community temp =
				new Community(this.getProfileName(),
				comunityName,
				desc);
		createdCommunities.add(temp);
	}//End class createCommunity
}//End class Account