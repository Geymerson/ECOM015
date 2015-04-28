public class SPlayer {
	//Player name
	private String playerName;
	
	//Player score
	private int playerScore;
	
	//Player profile
	private String playerProfile;
	
	public SPlayer() {
		this.playerName = "";
		this.playerScore = 0;
		this.playerProfile = "";
	}
	
	//Get player name
	public String getPlayerName() {
		return this.playerName;
	}//End method getPlayerName
	
	//Set player name
	public void setPlayerName(String name) {
		if(name != "") {
			this.playerName = name;
		}
	}//End method setPlayerName
	
	//Get player score
	public int getPlayerScore() {
		return this.playerScore;
	}//End method getPlayerScore
	
	//Set player score
	public void setPlayerScore(int score) {
		if(score >= 0) {
			this.playerScore = score;
		}
	}//End method setPlayerScore
	
	//Set player profile
	public void setPlayerProfile(String profile) {
		if(profile != "") {
			if(profile.length() > 4 && profile.substring(
					profile.length() - 4,profile.length()) == ".txt") {
				this.playerProfile = profile;
			}
			else {
				this.playerProfile = profile + ".txt";
			}
		}
	}//End method setPlayerProfile
	
	//Get player profile
	public String getPlayerProfile() {
		return this.playerProfile;
	}//End method getPlayerProfile
}