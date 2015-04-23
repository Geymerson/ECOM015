import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

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
			//Creating player's profile file
			bufferedWriter =
					new BufferedWriter(
							new FileWriter(playerProfile));
           // bufferedWriter.write('<');
            //Saving player's name
            bufferedWriter.write(player.getPlayerName());
            bufferedWriter.write(',');
            //Saving player's score
            bufferedWriter.write(player.getPlayerScore());
            bufferedWriter.write(',');
            //Saving player's board number
            bufferedWriter.write(board.getBoardNumber());
            bufferedWriter.write(",");
            //bufferedWriter.write(",[");
            
            //Save current player board state
            for(int row = 0; row < 9; row++) {
            	for(int column = 0; column < 9; column++) {
            		bufferedWriter.write('(');
            		bufferedWriter.write(row);
            		bufferedWriter.write(',');
            		bufferedWriter.write(column);
            		bufferedWriter.write(',');
            		bufferedWriter.write(
            				board.getCell(row,
            						column,
            						board.getPlayerBoard()));
            		bufferedWriter.write(')');
            		
            		if(!(row == 8 && column == 8)) {
            			bufferedWriter.write(',');
            		}
            	}
//            	if(row == 8){
//            		bufferedWriter.write("]>");
//            	}
//            	else {
//            		bufferedWriter.write("\n");
//            	}
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
	
	public void loadGame() throws IOException {
		String line;
		BufferedReader bufferedReader = null;
		try {
			//Creating player's profile file
			bufferedReader =
					new BufferedReader(
							new FileReader(
									player.getPlayerProfile()));
			line = bufferedReader.readLine();
			line.split(",");
        }
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
		}
		finally {
			bufferedReader.close();
		}
	}
}