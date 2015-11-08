package board;

import java.util.Random;

import cards.Card;
import cards.CardsFactory;

public class NormalBoard implements Board {
	
	private Card[] board;
	
	private Card background = new Card("cardBackground.png");
	
	private String lastClickedName;
	
	private int lastClickedNumber;

	public NormalBoard( ){
		lastClickedName = "";
		lastClickedNumber = 0;
		startBoard();
	}

	public void setCardBackground(Card background) {
		this.background = background;
	}
	
	public Card getCardBackground() {
		return background;
	}
	
	public void setBoard(Card [] board) {
		if(board != null && board.length == 16) {
			this.board = board;
		}
	}

	public Card[] getBoard() {
		return this.board;
	}

	public void startBoard() {
		this.board = new Card[16];
	}
	
	public void setLastClickedName(String lastClickedName) {
		this.lastClickedName = lastClickedName;
	}
	
	public String getLastClickedName() {
		return this.lastClickedName;
	}
	
	public void setLastClickedNumber(int lastClickedNumber) {
		this.lastClickedNumber = lastClickedNumber;
	}
	
	public int getLastClickednumber() {
		return this.lastClickedNumber;
	}
	
	public Card getCardAt(int pos) {
		return board[pos];
	}
	
	//Randomically fills the board
	public void launchBoard() {

		int filledCells = 1;
		Random randomNumber = new Random();
		String cardName;

		while(filledCells <= 16) {
			//A number between 0 and 15
			int index = randomNumber.nextInt(16);

			//If the board cell if is empty
			if(board[index] == null) {

				if(filledCells > 8) {
					cardName = "card" + (filledCells - 8) + ".png";
				}
				else {
					cardName = "card" + filledCells + ".png";
				}

				//Place card at index position
				board[index] = (Card) CardsFactory.getCard(cardName);
				filledCells++;
			}
		}//end loop
	}//end method launchBoard
	
	public void restartBoard() {
		startBoard();
		launchBoard();
	}
}//end class Board
