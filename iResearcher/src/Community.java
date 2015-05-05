import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Community {
	private String communityOwner;
	private String communityName;
	private String description;
	private ArrayList<String> members;

	Community(String owner, String communityName, String description) {
		setCommunityOwner(owner);
		setCommunityName(communityName);
		setDescription(description);
		members = new ArrayList<String>();
		members.add(owner);
		try {
			saveCommunity();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCommunityOwner(String owner) {
		if(owner != "") {
			this.communityOwner = owner;
		}
	}//End method setCommunityOwner

	public String getCommunityOwner() {
		return this.communityOwner;
	}//End method setCommunityOwner

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

	public void setDescription(String description) {
		if(description != "") {
			this.description = description;
		}
	}//End method setDescription

	public String getDescription() {
		return this.description;
	}//End method setDescription

	public void saveCommunity() throws IOException {
		//Refresh accountList file
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter =
					new BufferedWriter(
							new FileWriter("communities/"+
					this.getCommunityName()+".txt"));
			bufferedWriter.write(this.getCommunityOwner());
			bufferedWriter.write('\n');
			bufferedWriter.write(this.getDescription());
			bufferedWriter.write('\n');
			for(int i = 0; i < members.size(); i++) {
				bufferedWriter.write(members.get(i));
				bufferedWriter.write('\n');
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
	}//End method saveCommunity
}//End class Community