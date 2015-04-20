//import java.io.BufferedReader;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileWriter;

public class SOptions {
	private SBoard board;
	private SPlayer player;
	String playerProfile =
			"../GameFiles/"+
			player.getPlayerName()+ 
			".txt";
	
	public void restarGame() {
		board.restartBoard();
	}
	
	public void saveGame() throws IOException {		
		BufferedWriter bufferedWriter = null;		
		try {
			bufferedWriter =
					new BufferedWriter(
							new FileWriter(playerProfile));
            bufferedWriter.write('<');
            bufferedWriter.write(player.getPlayerName());
            bufferedWriter.write(',');
            bufferedWriter.write(player.getPlayerScore());
            bufferedWriter.write(board.getBoardNumber());
            bufferedWriter.write(',');
            
            for(int row = 0; row < 9; row++) {
            	for(int column = 0; column < 9; column++) {
            		board.getCell(row, column, board.getPlayerBoard());
            	}
            }
            player.setPlayerProfile(playerProfile);
        }
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
		}
        catch(IOException e) {
        	e.printStackTrace();
        }
		finally {
			bufferedWriter.close();
		}
	}
	
	public void newGame(String difficult)
			throws IOException {
		board.newBoard(difficult);
	}
	
	public void loadGame() {
		//TODO
	}
}