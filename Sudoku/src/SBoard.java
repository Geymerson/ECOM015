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
	private BufferedReader sudokuBoardFile = null;

	//Current player board number
	private int boardNumber;

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

	//Number of board loaded
	public void setBoardNumber(int number) {
		if((number >= 2 && number <= 11)||
				(number >= 13 && number <= 22)||
				(number >= 24 && number <= 33)) {
			this.boardNumber = number;
		}
		else {
			this.boardNumber = 0;
		}
	}//End method setBoardNumber

	//Get loaded board number
	public int getBoardNumber() {
		return this.boardNumber;
	}//End method getBoardNumber

	//Return game board
	public int[][] getGameBoard() {
		return this.gameBoard;
	}//End method getGameBoard

	//Set game board
	public void setGameBoard(int [][] board) {
		if(board != null) {
			this.gameBoard = board;
		}
	}//End method setGameBoard

	//Get board being modified by the player
	public int[][] getPlayerBoard() {
		return this.playerBoard;
	}//End method getPlayerBoard

	//Set board that will be modified by the player
	public void setPlayerBoard(int [][] board) {
		if(board != null) {
			this.playerBoard = board;
		}
	}//End method setPlayerBoard

	//Get board initial state
	public int[][] getRestartBoard() {
		return this.restartBoard;
	}//End method getRestartBoard

	//Set board initial state
	public void setRestartBoard(int [][] board) {
		if(board != null) {
			this.restartBoard = board;
		}
	}//End method setRestartBoard

	//Set board cell
	public void setCell(int row, int column, int value, int board[][]) {
		if(row < 0 || row >= 9 || column < 0 || column >= 9
				|| value < 0 || value > 9) {
			return;
		}
		board[row][column] = value;
	}//End method setCell

	//Get board cell
	public int getCell(int row, int column, int board[][]) {
		if(row < 0 || row >= 9 || column < 0 || column >= 9) {
			return -1;
		}
		return board[row][column];
	}//End method getCell	

	//Load game board
	public boolean launchGameBoard(String difficult) throws IOException {
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
					randomicNumberGenerator.nextInt(10) + 2;
			//System.out.printf("BNum %d\n", boardNum);

			if(difficult == "Medium") {
				boardNum += 11;
			}
			else if(difficult == "Hard") {
				boardNum += 22;
			}
			//System.out.printf("BNum %d\n", boardNum);
			this.setBoardNumber(boardNum);
		}

		try {
			this.sudokuBoardFile =
					new BufferedReader(
							new FileReader("GameFiles/sudokuBoards.txt"));
			while((line = sudokuBoardFile.readLine())  != null) {
				if(this.getBoardNumber() == (lineCounter + 1)) {
					int linePosition = 0;
					for(int row = 0; row < 9; row++) {
						for(int column = 0; column < 9; column++) {
							int value = Integer.parseInt(
									Character.toString(line.charAt(linePosition)));
							
							setCell(row,
									column,
									value,
									this.getGameBoard());
							linePosition++;
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
		catch(IOException ee) {
			ee.printStackTrace();
		}
		finally {
			this.sudokuBoardFile.close();
		}
		return true;
	}//End method launchGameBoard

	//Load player board
	public void launchPlayerBoard() {		
		Random randomicNumberGenerator = new Random();
		for(int row = 0; row < 9; row++) {
			int counter = 1;
			int column;
			while(counter <= 3) {
				//Choose a random column between 0 ~ 8
				column = randomicNumberGenerator.nextInt(9);
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

	//Load restart board
	public void restartBoard() {
		this.setPlayerBoard(this.getRestartBoard());
	}//End method restartBoard

	//Load a new board
	public void newBoard(String difficult) throws IOException {
		this.setBoardNumber(0);
		this.launchGameBoard(difficult);
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				this.setCell(row,
						column,
						0,
						this.getPlayerBoard());
			}
		}
		this.launchPlayerBoard();
	}//End method newBoard
}//End class SBoard