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
	private BufferedReader sudokuBoardFile;
	
	//Current player board number
	private int boardNumber;
	
	//private String[] difficult = {"Easy","Medium","Hard"};
	
	public SBoard() {
		//Initiate boards
		this.gameBoard = new int[9][9];
		this.playerBoard = new int[9][9];
		this.boardNumber = 0;
		
		//Set all boards cells to a not filled state "0"
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				this.setCell(row, column, 0, this.getPlayerBoard());
			}
		}
	}
	
	public void setBoardNumber(int number) {
		if((number >= 2 && number <= 11)||
				(number >= 13 && number <= 22)||
				(number >= 24 && number <= 33)) {
			this.boardNumber = number;
		}
		else {
			this.boardNumber = 0;
		}
	}
	
	public int getBoardNumber() {
		return this.boardNumber;
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
	
	public boolean launchGameBoard(String difficult)
			throws IOException {
		//File line receiver
		String line;
		
		//Line where the board is located
		int lineCounter = 0;
		
		//Set a board if it isn't settled yet		
		if(this.getBoardNumber() == 0) {
			//Random number generator to pick up
			//random boards
			Random randomicNumberGenerator = new Random();
			
			//Choose a random "difficult" board
			//Default difficult (easy 2 ~ 11)
			int boardNum =
					//Generate a random number between (2~11)
					randomicNumberGenerator.nextInt(10) + 1;

			if(difficult == "Medium") {
				boardNum += 11;
			}
			else if(difficult == "Hard") {
				boardNum += 22;
			}
			
			this.setBoardNumber(boardNum);
		}
		
		try {
			this.sudokuBoardFile =
					new BufferedReader(
							new FileReader("../GameFiles/sudokuBoards.txt"));
			while((line = sudokuBoardFile.readLine()) != null) {
				if(this.getBoardNumber() == (lineCounter + 1)) {
					for(int i = 0; i < line.length(); i++) {
						for(int row = 0; row < 9; row++) {
							for(int column = 0; column < 9; column++) {
								setCell(row,
										column,
										line.charAt(i),
										this.getGameBoard());
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
			e.printStackTrace();
			return false;
		}
		finally {
			sudokuBoardFile.close();
		}
		return true;
	}//End method launchGameBoard
	
	public void launchPlayerBoard() {
		Random randomicNumberGenerator = new Random();
		for(int row = 0; row < 9; row++) {
			int counter = 1;
			int column;
			while(counter <= 3) {
				//Choose a random column between 0 ~ 8
				column = randomicNumberGenerator.nextInt(9) - 1;
				//If the player board cell is empty
				if(this.getCell(row,column, this.getPlayerBoard()) == 0) {
					//Set player board cell same as game board cell
					this.setCell(row,
							column,
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