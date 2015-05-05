import java.util.ArrayList;

public class Account {
	//Account Personal information
	private String accountLogin;
	private String accountPassword;
	
	//Profile
	private String profileName;
	private ArrayList<Message> messages;
	private ArrayList<String> friendRequest;
	private ArrayList<String> friendList;
	private ArrayList<Community> createdCommunities;
	
	public void createProfile(String profileName) {
		if(profileName != "") {
			this.profileName = profileName;
			messages = new ArrayList<Message>();
			friendRequest = new ArrayList<String>();
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
	
	public void newMessage(Message message) {
		messages.add(message);
	}//End method newMessage
	
	public void clearMessages() {
		messages.clear();
	}//End method clearMessages
	
	
	public void addFriend(String friendName) {
		friendList.add(friendName);
	}//End method addFriend
	
	public void clearFriendList() {
		friendList.clear();
	}//End method clearFriendList
	
	public void editProfile() {
		//clear messages
		//clear friendlist
		//delete comunity
		
		switch(233){
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:	
				break;
		}
	}
	
	public void createCommunity(String comunityName) {
		Community temp = new Community(comunityName);
		createdCommunities.add(temp);
	}
}//End class Account