import java.io.IOException;


public interface SBoardInterface {
	//Set board number
	public void setBoardNumber(int number);
	
	//Return board number
	public int getBoardNumber();
	
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
	
	//Launch game board
	public boolean launchGameBoard(String difficult) throws IOException;
	
	//Launch player board
	public void launchPlayerBoard();
	
	//Restart game board
	public void restartBoard();
	
	//Launch a new board
	public void newBoard(String difficult) throws IOException;;
}//End interface SBoardInterface
