package boardUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import board.Board;

public interface State {
	public void updateState(Context context);
	public void updateTextArea(JTextArea textArea);
	public void updateBoard(Board board, JLabel[] cards, boardUI.BoardUI.MouseHandler mouseHandler);
	public void updateBoard(Board board, JLabel[] cards, JPanel panel);
}
