public class SRules {
	public boolean validateBoard(int [][] board) {
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column++) {
				int atPosition = 0;
				int underVerificationValue = board[row][column];
				while(atPosition < 9) {
					//incomplete board 
					if(underVerificationValue == 0) {
						return false;
					}
					//Checking row
					else if((board[row][atPosition] == underVerificationValue ||
							board[row][atPosition] == 0) &&
							atPosition != column) {
						return false;
					}
					//Checking column
					else if((board[atPosition][column] == underVerificationValue ||
							board[atPosition][column] == 0) &&
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