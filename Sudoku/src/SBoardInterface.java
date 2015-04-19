import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public interface SBoardInterface {
	//Return game board
	public int[][] getGameBoard();
	
	//Set game board
	public void setGameBoard(int [][] board);
	
	//Return player board
	public int[][] getPlayerBoard();
	
	//Set player board
	public void setPlayerBoard(int [][] board);
	
	//Return restart board
	public int[][] getRestartBoard();
	
	//Set restart board
	public void setRestartBoard(int [][] board);

	//Set "value" at the cell at position(row, column) with in the board
	public void setCell(int row, int column, int value, int board[][]);
	
	//Return the value at position (row, column) from the board 
	public int getCell(int row, int column, int board[][]);
	
	public boolean launchGameBoard(String difficult) throws IOException;
	
	//Launch player board
	public void launchPlayerBoard();
	
	//Restart game board
	public void restartBoard();
	
	//Launch a new board
	public void newBoard(String difficult) throws IOException;;
	
	//Save current board
	//public void saveBoard();
	
	//Load previous board
	//public void loadBoard();
}
