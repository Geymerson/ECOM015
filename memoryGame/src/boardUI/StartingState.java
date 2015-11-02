package boardUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import board.Board;

public class StartingState implements State {

	public void updateState(Context context) {
		context.setState(this);
	}

	public void updateTextArea(JTextArea textArea) {
		textArea.append("\nTime is over, you might start now.");
	}

	public void updateBoard(Board board, JLabel[] cards, JPanel panel) {
		// TODO Auto-generated method stub
	}

	public void updateBoard(Board board, JLabel[] cards,
			boardUI.BoardUI.MouseHandler mouseHandler) {
		
		if(board.getBoard().length == cards.length) {
			for(int i = 0; i < board.getBoard().length; i++) {
				cards[i].setIcon(board.getCardBackground().getCardIconImage());
				
				if(cards[i].getMouseListeners().length == 0) {
					cards[i].addMouseListener(mouseHandler);
				}
			}
		}
	}
}
