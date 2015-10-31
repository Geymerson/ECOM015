package boardUI;

import javax.swing.JLabel;

import board.Board;

public class Memento {
	private JLabel[] cardsState;
	private Board boardState;

	public Memento(JLabel[] state){
		this.cardsState = state;
	}
	
	public Memento(Board state){
		this.boardState = state;
	}
	
	public Memento(JLabel[] cardsState, Board boardState){
		this.cardsState = cardsState;
		this.boardState = boardState;
	}

	public JLabel[] getCardsState(){
		return cardsState;
	}
	
	public Board getBoardState(){
		return boardState;
	}
}
