import java.io.*;

public class SBoard {
	//Game board
	private int [][] board;
	
	//Player board;
	private int [][] playerBoard;
	
	//Filled cells verifier
	private int [] filledCells = new int[81];
	
	//Sudoku ready board
	private FileReader sudokuBoard;
	
	SBoard() {
		try {
			sudokuBoard = new FileReader("../GameFiles/sudokuBoards.txt");
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
		}
	}

	public void setCell(int row, int column, int value) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			return;
		}
		board[row][column] = value;
	}//End method setCell
	
	public int getCell(int row, int column) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			return -1;
		}
		return board[row][column];
	}//End method getCell
	
	public void showSolution() {
		//TODO
	}
	
	public void saveBoard() {
		//TODO
	}
	
	public void launchBoard() {
		//TODO
	}//End method launchBoard
}