import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SOptions {
	//private SBoard board;
	//private SPlayer player;
	//private String playerProfile; 
	
//	public SOptions() {
//		playerProfile = "";
//	}
//	
//	public void setPlayerProfile(String playerName) {
//		if(playerName != "") {
//			playerProfile = playerName +".txt";
//		}
//	}
	
	public void restarGame(SBoard board) {
		board.restartBoard();
	}
	
	public void saveGame(SPlayer player, SBoard board) throws IOException {		
		BufferedWriter bufferedWriter = null;		
		try {
			//Creating player's profile file
			bufferedWriter =
					new BufferedWriter(
							new FileWriter("GameFiles/"
									+player.getPlayerProfile()));
            //Saving player's name
            bufferedWriter.write(player.getPlayerName());
            bufferedWriter.write(',');
            //Saving player's score
            bufferedWriter.write(player.getPlayerScore());
            bufferedWriter.write(',');
            //Saving player's board number
            bufferedWriter.write(board.getBoardNumber());
            bufferedWriter.write(",");
            
            //Save current player board state
            for(int row = 0; row < 9; row++) {
            	for(int column = 0; column < 9; column++) {
            		bufferedWriter.write('(');
            		bufferedWriter.write(row);
            		bufferedWriter.write(';');
            		bufferedWriter.write(column);
            		bufferedWriter.write(';');
            		bufferedWriter.write(
            				board.getCell(row,
            						column,
            						board.getPlayerBoard()));
            		bufferedWriter.write(')');
            		if(!(row == 8 && column == 8)) {
            			bufferedWriter.write(',');
            		}
            	}
            }
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
	
	public void newGame(String difficult, SBoard board)
			throws IOException {
		board.newBoard(difficult);
	}
	
	public void loadGame(SPlayer player) throws IOException {
		String line;
		BufferedReader bufferedReader = null;
		try {
			//Creating player's profile file
			bufferedReader =
					new BufferedReader(
							new FileReader(
									"GameFiles/"
							+player.getPlayerProfile()));
			line = bufferedReader.readLine();
			String[] temp = line.split(",");
			for(int i = 0; i < temp.length; i++) {
				if(i > 2) {
					System.out.println(temp[i].substring(1,6));
				}
				else {
					System.out.println(temp[i]);
				}
			}
        }
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Profile doesn't exist.",
					"Warning", JOptionPane.ERROR_MESSAGE);
			//System.out.println("File could not be opened!");
		}
		finally {
			bufferedReader.close();
		}
	}
}