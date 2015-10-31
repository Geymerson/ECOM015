package boardUI;

import javax.swing.JLabel;

import board.Board;

public class Originator {
	private JLabel[] cardsState;
	private Board boardState;

	public void setCardsState(JLabel[] state){
		this.cardsState = state;
	}
	
	public void setBoardState(Board state){
		this.boardState = state;
	}

	public JLabel[] getCardsState(){
		return cardsState;
	}
	
	public Board getBoardState(){
		return boardState;
	}

	public Memento saveCardsStateToMemento(){
		return new Memento(cardsState);
	}
	
	public Memento saveBoardStateToMemento(){
		return new Memento(boardState);
	}
	
	public Memento saveCardsAndBoardStateToMemento(){
		return new Memento(cardsState, boardState);
	}

	public void getCardsStateFromMemento(Memento Memento){
		cardsState = Memento.getCardsState();
	}
	
	public void getBoardStateFromMemento(Memento Memento){
		boardState = Memento.getBoardState();
	}
}
