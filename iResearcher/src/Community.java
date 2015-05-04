import java.util.ArrayList;

public class Community {
	private String communityOwner;
	private String communityName;	
	private ArrayList<String> members;
	
	Community(String communityName) {
		setCommunityName(communityName);
		members = new ArrayList<String>();
	}
	
	public void setCommunityName(String comunityName) {
		if(comunityName != "") {
			this.communityName = comunityName;
		}
	}//End method setComunityName
	
	public String getCommunityName() {
		return this.communityName;
	}//End method getComunityName
	
	public void addMember(String newMember) {
		members.add(newMember);
	}//End method addMember
	
	private void removeMember(String member) {
		members.remove(member);
	}//End method removeMember
}//End class Community