import java.io.IOException;

public class SOptions {
	public void restarGame(SBoard game) {
		game.restartBoard();
	}
	public void saveGame() {
		//TODO
	}
	
	public void newGame(SBoard game, String difficult) throws IOException {
		game.newBoard(difficult);
	}
	
	public void loadGame() {
		//TODO
	}
}