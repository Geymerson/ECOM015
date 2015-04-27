import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SOptions {
	
	public void saveGame(SPlayer player, SBoard board) throws IOException {		
		BufferedWriter bufferedWriter = null;		
		try {
			//Creating player's profile file
			bufferedWriter =
					new BufferedWriter(
							new FileWriter("GameFiles/"+
					player.getPlayerProfile()));
            //Saving player's name
            bufferedWriter.write(player.getPlayerName());
            bufferedWriter.write(',');
            //Saving player's score
            bufferedWriter.write(Integer.toString(player.getPlayerScore()));
            bufferedWriter.write(',');
            //Saving player's board number
            bufferedWriter.write(Integer.toString(board.getBoardNumber()));
            bufferedWriter.write(",");
            
            //Save current player board state
            for(int row = 0; row < 9; row++) {
            	for(int column = 0; column < 9; column++) {
            		bufferedWriter.write('(');
            		bufferedWriter.write(Integer.toString(row));
            		bufferedWriter.write(';');
            		bufferedWriter.write(Integer.toString(column));
            		bufferedWriter.write(';');
            		bufferedWriter.write(Integer.toString(
            				board.getCell(row,
            						column,
            						board.getPlayerBoard())));
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
	
	public void loadGame(String profileName, SPlayer player,
			SBoard board) throws IOException {
		String line;
		BufferedReader bufferedReader = null;
		try {
			//Opening player's profile file
			bufferedReader =
					new BufferedReader(
							new FileReader(
									"GameFiles/"+
							profileName + ".txt"));
			line = bufferedReader.readLine();
			String[] temp = line.split(",");
			//Setting player information from profile file
			player.setPlayerName(temp[0]);
			player.setPlayerScore(Integer.parseInt(temp[1]));
			board.setBoardNumber(Integer.parseInt(temp[2]));
			board.launchGameBoard("");
			
			for(int i = 3; i < temp.length; i++) {
				//temp[0] == row, temp[1] == column, temp[2] == value
				String[] temp1 = temp[i].substring(1,6).split(";");
				board.setCell(Integer.parseInt(temp1[0]),
						Integer.parseInt(temp1[1]),
						Integer.parseInt(temp[2]),
						board.getPlayerBoard());
			}
        }
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Profile doesn't exist.",
					"Warning", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ee) {
			ee.printStackTrace();
		}
		finally {
			bufferedReader.close();
		}
	}//End method loadGame
	
//	ArrayList<SPlayer> loadRank() throws IOException {
//		String line;
//		SPlayer player = new SPlayer();
//		ArrayList<SPlayer> playerList = new ArrayList<SPlayer>();
//		BufferedReader bufferedReader = null;
//		String[] temp;
//		try {
//			//Opening player's profile file
//			bufferedReader =
//					new BufferedReader(
//							new FileReader("GameFiles/ranking.txt"));
//			while((line = bufferedReader.readLine()) != null) {
//				temp = line.split(",");				
//				//System.out.println(temp[0]);
//				player.setPlayerName(temp[0]);
//				
//				//System.out.println(temp[1]);
//				player.setPlayerScore(Integer.parseInt(temp[1]));
//				
//				playerList.add(player);
//				//System.out.print(playerList.size());
//			}
//		}
//		catch(FileNotFoundException e) {
//			JOptionPane.showMessageDialog(null,
//					"Profile doesn't exist.",
//					"Warning", JOptionPane.ERROR_MESSAGE);
//		}
//		catch(IOException ee) {
//			ee.printStackTrace();
//		}
//		finally {
//			bufferedReader.close();
//		}
//		return playerList;
//	}//End method loadRank
}