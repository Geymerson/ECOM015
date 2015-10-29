package player;

public class Player {
	private String name;
	
	Player(String playerName) {
		setName(playerName);
	}
	
	public void setName(String playerName) {
		if(playerName != "") {
			name = playerName;
		}
	}//end method setName
	
	public String getName() {
		return this.name;
	}//end method getName
}//end class Player
