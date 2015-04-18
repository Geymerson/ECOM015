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
	
	public int getPlayerScore(int score) {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}