import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Community {
	private String communityOwner;
	private String communityName;
	private String description;
	private ArrayList<String> members;
	BufferedReader bufferedReader;
	
	Community() {
		members = new ArrayList<String>();
	}

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

	public void setCommunityName(String communityName) {
		if(communityName != "") {
			this.communityName = communityName;
		}
	}//End method setComunityName

	public String getCommunityName() {
		return this.communityName;
	}//End method getComunityName

	public void addMember(String newMember) {
		members.add(newMember);
	}//End method addMember

	public void removeMember(String member) {
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
			bufferedWriter.write(this.getDescription());
			bufferedWriter.write('\n');
			bufferedWriter.write(this.getCommunityOwner());
			bufferedWriter.write('\n');
			for(int i = 1; i < members.size(); i++) {
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
	
	public void loadCommunity(String communityName)
			throws IOException {
		try {
			members.clear();
			this.setCommunityName(communityName);
			bufferedReader = new BufferedReader(
					new FileReader("communities/"+communityName+".txt"));
			String line;
			for(int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				if(i > 0) {
					members.add(line);
					if(i == 1) {
						this.setCommunityOwner(members.get(i));
					}
				}
			}
		}
		catch(FileNotFoundException e1) {
			System.out.println("Community doesn't exists");
		}
		catch (IOException e3) {
			e3.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
	}//End method loadCommunity
}//End class Community