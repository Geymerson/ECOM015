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
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String name) {
		if(name != "") {
			this.playerName = name;
		}
	}
	
	public int getPlayerScore() {
		return this.playerScore;
	}
	
	public void setPlayerScore(int score) {
		if(score >= 0) {
			this.playerScore = score;
		}
	}
	
	public void setPlayerProfile(String profile) {
		if(profile != "") {
			if(profile.substring(
					profile.length() - 4,profile.length()) == ".txt") {
				this.playerProfile = profile;
			}
			else {
				this.playerProfile = profile + ".txt";
			}
		}
	}
	
	public String getPlayerProfile() {
		return this.playerProfile;
	}
}