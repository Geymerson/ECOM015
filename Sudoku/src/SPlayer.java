public class SPlayer {
	//Player name
	private String playerName;
	
	//Player score
	private int score;
	
	//Player profile
	private String playerProfile;
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String name) {
		if(name != "") {
			this.playerName = name;
		}
	}
	
	public int getPlayerScore() {
		return this.score;
	}
	
	public void setPlayerScore(int score) {
		if(score >= 0) {
			this.score = score;
		}
	}
	
	public void setPlayerProfile(String profile) {
		if(profile != "") {
			this.playerProfile = profile;
		}
	}
	
	public String getPlayerProfile() {
		return this.playerProfile;
	}
}