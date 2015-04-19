import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class SBoard implements SBoardInterface {
	//Game board
	private int [][] gameBoard;
	
	//Player board;
	private int [][] playerBoard;
	
	//Restart board
	private int [][] restartBoard;
	
	//Sudoku ready board
	private BufferedReader sudokuBoard;
	
	//private String[] difficult = {"Easy","Medium","Hard"};
	
	SBoard() {
		//Initiate boards
		this.gameBoard = new int[9][9];
		this.playerBoard = new int[9][9];
		
		//Set all boards cells to a not filled state "0"
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				this.setCell(row, column, 0, this.getPlayerBoard());
			}
		}
	}
	
	public int[][] getGameBoard() {
		return this.gameBoard;
	}
	
	public void setGameBoard(int [][] board) {
		if(board != null) {
			this.gameBoard = board;
		}
	}
	
	public int[][] getPlayerBoard() {
		return this.playerBoard;
	}
	
	public void setPlayerBoard(int [][] board) {
		if(board != null) {
			this.playerBoard = board;
		}
	}
	
	public int[][] getRestartBoard() {
		return this.restartBoard;
	}
	
	public void setRestartBoard(int [][] board) {
		if(board != null) {
			this.restartBoard = board;
		}
	}

	public void setCell(int row, int column, int value, int board[][]) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			return;
		}
		board[row][column] = value;
	}//End method setCell
	
	public int getCell(int row, int column, int board[][]) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			return -1;
		}
		return board[row][column];
	}//End method getCell	
	
	public boolean launchGameBoard(String difficult) throws IOException {
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
			this.sudokuBoard = new BufferedReader(
							new FileReader("../GameFiles/sudokuBoards.txt"));
			while((line = sudokuBoard.readLine()) != null) {
				if(boardNumber == (lineCounter + 1)) {
					for(int i = 0; i < line.length(); i++) {
						for(int row = 0; row < 9; row++) {
							for(int column = 0; column < 9; column++) {
								this.gameBoard[row][column] = line.charAt(i); 
							}
						}
					}
				}
				lineCounter++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be opened!");
			return false;
		}
		catch(IOException e) {
			System.out.printf("Exception occurred trying to read %s",
					this.sudokuBoard);
			return false;
		}
		finally {
			sudokuBoard.close();
		}
		return true;
	}//End method launchGameBoard
	
	//Launch player board
	public void launchPlayerBoard() {
		Random randomicNumberGenerator = new Random();
		for(int row = 0; row < 9; row++) {
			int counter = 1;
			int column;
			while(counter <= 3) {
				//Choose a random column between 0 ~ 8
				column = randomicNumberGenerator.nextInt(9) - 1;
				//If the player board cell is empty
				if(this.getCell(row, column, this.getPlayerBoard()) == 0) {
					//Set player board cell same as game board cell
					this.setCell(row, column,
							//Cell from the game board
							this.getCell(row, column, this.getGameBoard()),
							this.getPlayerBoard());
					counter++;
				}
			}//End loop while
		}//End loop for
		this.setRestartBoard(this.getPlayerBoard());
	}//End method launchPlayerBoard
	
	public void restartBoard() {
		this.setPlayerBoard(this.getRestartBoard());
	}
	
	public void newBoard(String difficult) throws IOException {
		this.launchGameBoard(difficult);
		this.launchPlayerBoard();
	}
}//End class SBoard