import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SOptions {
	
	public void saveGame(SPlayer player, SBoard board)
			throws IOException {		
		BufferedWriter playerBufferedWriter = null;
		BufferedWriter rankingBufferedWriter = null;
		try {
			
			//Inserting player in the rank
			rankingBufferedWriter =
					new BufferedWriter(
							new FileWriter("GameFiles/ranking.txt", true));
			rankingBufferedWriter.write(player.getPlayerName());
			rankingBufferedWriter.write(',');
			rankingBufferedWriter.write(Integer.toString(player.getPlayerScore()));
			rankingBufferedWriter.write('\n');
			
			//Creating player's profile file
			playerBufferedWriter =
					new BufferedWriter(
							new FileWriter("GameFiles/"+
					player.getPlayerProfile()));
            //Saving player's name
			playerBufferedWriter.write(player.getPlayerName());
			playerBufferedWriter.write(',');
            //Saving player's score
			playerBufferedWriter.write(Integer.toString(player.getPlayerScore()));
			playerBufferedWriter.write(',');
            //Saving player's board number
			playerBufferedWriter.write(Integer.toString(board.getBoardNumber()));
			playerBufferedWriter.write(",");
            
            //Save current player board state
            for(int row = 0; row < 9; row++) {
            	for(int column = 0; column < 9; column++) {
            		playerBufferedWriter.write('(');
            		playerBufferedWriter.write(Integer.toString(row));
            		playerBufferedWriter.write(';');
            		playerBufferedWriter.write(Integer.toString(column));
            		playerBufferedWriter.write(';');
            		playerBufferedWriter.write(Integer.toString(
            				board.getCell(row,
            						column,
            						board.getPlayerBoard())));
            		playerBufferedWriter.write(';');
            		playerBufferedWriter.write(Integer.toString(
            				board.getCell(row,
            						column,
            						board.getRestartBoard())));
            		playerBufferedWriter.write(')');
            		if(!(row == 8 && column == 8)) {
            			playerBufferedWriter.write(',');
            		}
            	}
            }
        }
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
		}
        catch(IOException ee) {
        	ee.printStackTrace();
        }
		finally {
			playerBufferedWriter.close();
			rankingBufferedWriter.close();
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
				//temp1[0] == row, temp1[1] == column,
				//temp1[2] == value, temp1[3] == restartValue
				String[] temp1 = temp[i].substring(1,8).split(";");
				board.setCell(Integer.parseInt(temp1[0]),
						Integer.parseInt(temp1[1]),
						Integer.parseInt(temp1[2]),
						board.getPlayerBoard());
				
				board.setCell(Integer.parseInt(temp1[0]),
						Integer.parseInt(temp1[1]),
						Integer.parseInt(temp1[3]),
						board.getRestartBoard());
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
	
	ArrayList<SPlayer> loadRank() throws IOException {
		String line;
		ArrayList<SPlayer> playerList = new ArrayList<SPlayer>();
		BufferedReader bufferedReader = null;
		String[] temp;
		try {
			//Opening player's profile file
			bufferedReader =
					new BufferedReader(
							new FileReader("GameFiles/ranking.txt"));
			while((line = bufferedReader.readLine()) != null) {
				SPlayer player = new SPlayer();
				temp = line.split(",");				
				player.setPlayerName(temp[0]);
				player.setPlayerScore(Integer.parseInt(temp[1]));
				playerList.add(player);
				playerList = this.sortPlayerList(playerList);
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
		return playerList;
	}//End method loadRank
	
	public ArrayList<SPlayer> sortPlayerList(ArrayList<SPlayer> list) {
		ArrayList<SPlayer> tempList = list;
		SPlayer tempPlayer;
		boolean swap = true;
		
		while(swap) {
			swap = false;
			for(int i = 0; i < tempList.size() - 1; i++) {
				if(tempList.get(i).getPlayerScore() <
				tempList.get(i + 1).getPlayerScore()){ 
					tempPlayer = tempList.get(i);
					tempList.set(i, tempList.get(i + 1));
					tempList.set(i + 1, tempPlayer);
					swap = true;
				}
			}
		}
		return tempList;
	}
}