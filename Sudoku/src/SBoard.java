import java.io.*;
import java.util.Random;

public class SBoard {
	//Game board
	private int [][] board;
	
	//Player board;
	private int [][] playerBoard;
	
	//Filled cells verifier
	private int [] filledCells;
	
	//Sudoku ready board
	private BufferedReader sudokuBoard;
	
	//private String difficult;
	
	SBoard(String difficult) {
		//Initiate boards
		this.board = new int[9][9];
		this.playerBoard = new int[9][9];
		
		//Set all boards cells to a not filled state "0"
		this.filledCells = new int[81];
		
		for(int i = 0; i < 81; i++) {
			this.filledCells[i] = 0;
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
	
//	public void printBoard() {
//		
//	}
	
	public void saveBoard() {
		//TODO
	}
	
	public void launchBoard(String difficult) throws IOException {
		
		//File line receiver
		String line;
		
		//Line where the board is located
		int lineCounter = 0;
		
		//Random number generator to pick up
		//random boards
		Random randomicNumberGenerator = new Random();
		
		//Choose a random "difficult" board
		//Default difficult (easy 2 ~ 11)
		int boardNumber =
				//Generate a random number between (2~11)
				randomicNumberGenerator.nextInt(10) + 1;

		if(difficult == "Medium") {
			boardNumber += 11;
		}
		else if(difficult == "Hard") {
			boardNumber += 22;
		}
		
		try {
			sudokuBoard = new BufferedReader(
							new FileReader("../GameFiles/sudokuBoards.txt"));
			while((line = sudokuBoard.readLine()) != null) {
				if(boardNumber == (lineCounter + 1)) {
					for(int i = 0; i < line.length(); i++) {
						for(int row = 0; row < 9; row++) {
							for(int column = 0; column < 9; column++) {
								board[row][column] = line.charAt(i); 
							}
						}
					}
				}
				lineCounter++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
		}
		catch(IOException e) {
			System.out.printf("Exception occurred trying to read %s",
					sudokuBoard);
			return;
		}
		finally {
			sudokuBoard.close();
		}
	}//End method launchBoard
}