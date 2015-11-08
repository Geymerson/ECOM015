package board;

import cards.Card;

public interface Board {
	
	public void setCardBackground(Card background);
	
	public Card getCardBackground();
	
	public void setBoard(Card [] board);

	public Card[] getBoard();

	public void startBoard();
	
	public void setLastClickedName(String lastClickedName);
	
	public String getLastClickedName();
	
	public void setLastClickedNumber(int lastClickedNumber);
	
	public int getLastClickednumber();
	
	public Card getCardAt(int pos);
	
	public void launchBoard();
}
