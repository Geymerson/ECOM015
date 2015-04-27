public class SRules {
	public boolean validateBoard(int [][] board) {
		int [][] tempBoard = board;
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int atPosition = 0;
				int underVerificationValue = tempBoard[row][column];
				while(atPosition < 9) {
					//Empty cell 
					if(underVerificationValue == 0) {
						return false;
					}
					//Checking row
					else if((tempBoard[row][atPosition] == underVerificationValue ||
							tempBoard[row][atPosition] == 0) &&
							atPosition != column) {
						return false;
					}
					//Checking column
					else if((tempBoard[atPosition][column] == underVerificationValue ||
							tempBoard[atPosition][column] == 0) &&
							atPosition != row) {
						return false;
					}
					atPosition++;
				}
			}
		}
		return true;
	}
}