package boardUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import board.Board;

public class MemorizingState implements State {

	public void updateState(Context context) {
		context.setState(this);
	}

	public void updateTextArea(JTextArea textArea) {
		textArea.append("You have 5 seconds to memorize.");
	}

	public void updateBoard(Board board, JLabel[] cards, JPanel panel) {
		if(board.getBoard().length == cards.length) {
			for(int i = 0; i < board.getBoard().length; i++) {
				cards[i] = new JLabel("", 
						board.getCardAt(i).getCardIconImage(),
						JLabel.CENTER);
				panel.add(cards[i]);
			}
		}
	}

	public void updateBoard(Board board, JLabel[] cards,
			boardUI.BoardUI.MouseHandler mouseHandler) {
		// TODO Auto-generated method stub
	}
}
