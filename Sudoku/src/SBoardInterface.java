import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public interface SBoardInterface {
	public int[][] getGameBoard();
	
	public void setGameBoard(int [][] board);
	
	public int[][] getPlayerBoard();
	
	public void setPlayerBoard(int [][] board);

	public void setCell(int row, int column, int value, int board[][]);
	
	public int getCell(int row, int column, int board[][]);
	
	public boolean launchGameBoard(String difficult) throws IOException;
	
	//Launch player board
	public void launchPlayerBoard();
}
