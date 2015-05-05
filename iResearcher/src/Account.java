import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	//Account Personal information
	private String accountLogin;
	private String accountPassword;
	private String profileName;
	private ArrayList<String> friendList;
	private AccountManager manager = new AccountManager();

	public void createProfile(String profileName) {
		if(profileName != "") {
			this.profileName = profileName;
			friendList = new ArrayList<String>();
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
		if(profileName != "") {
			this.profileName = profileName;
		}
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
			String cmtName1 = userInput.nextLine();
			System.out.println("Description:");
			String desc = userInput.nextLine();
			this.createCommunity(cmtName1, desc);
			break;
		case 4:
			manager.sendMessage(this);
			break;
		case 5:
			manager.friendRequest(this);
			break;
		case 6:
			System.out.println("Community Name:");
			String cmtName2 = userInput.nextLine();
			Community tempComm = new Community();
			tempComm.loadCommunity(cmtName2);
			if(this.getLogin() == tempComm.getCommunityOwner()) {
				while(true) {
					System.out.println("Press A to add a new member");
					System.out.println("Press R to remove");
					System.out.println("Press any other key to cancel");
					String ck = userInput.nextLine();

					if(ck == "A" || ck == "a") {
						System.out.println("New member name:");
						String tempName = userInput.nextLine();
						File tempFile = new File("accounts/"+tempName+".txt");
						//If the user exists
						if(tempFile.isFile()) {
							tempComm.addMember(tempName);
						}
						tempComm.saveCommunity();
					}
					else if(ck == "R" || ck == "r") {
						System.out.println("Member to be removed:");
						String tempName = userInput.nextLine();
						File tempFile = new File("accounts/"+tempName+".txt");
						if(tempFile.isFile()) {
							tempComm.removeMember(tempName);
						}
						tempComm.saveCommunity();
					}
					else {
						break;
					}
				}
			}
			break;
		default:
			break;
		}
	}

	public void createCommunity(String comunityName, String desc) {
		Community temp =
				new Community(this.getLogin(),
						comunityName,
						desc);
	}//End class createCommunity
}//End class Account