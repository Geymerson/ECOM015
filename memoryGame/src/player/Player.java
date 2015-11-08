package player;

public class Player {
	
	private String name;
	
	private int score;
	
	public Player(String playerName) {
		setName(playerName);
		setPlayerScore(0);
	}
	
	public void setName(String playerName) {
		if(playerName != "" && playerName != null) {
			name = playerName;
		}
		else {
			name = "Player";
		}
	}//end method setName
	
	public String getName() {
		return this.name;
	}//end method getName
	
	public void setPlayerScore(int score) {
		this.score = score;
	}
	
	public int getPlayerScore() {
		return this.score;
	}
}//end class Player
