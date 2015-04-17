public class SBoard {
	//Game board
	private int [][] board;
	
	//Player board;
	private int [][] playerBoard;
	
	//Filled cells verifier
	private int [] filledCells = new int[81];
	
	public void setCell(int row, int column, int value) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			//TODO
		}
		board[row][column] = value;
	}
	
	public int getCell(int row, int column) {
		if(row < 0 || row >= 9 || column <= 0 || column >= 9) {
			return -1;
		}
		return board[row][column];
	}
	
	public void showSolution() {
		//TODO
	}
	
	public void saveBoard() {
		//TODO
	}
	
	public void launchBoard() {
		//TODO
	}
}