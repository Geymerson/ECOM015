import java.io.IOException;

import javax.swing.JFrame;

public class SudokuTest {
	public static void main(String[] args) {
		SGUI testGui = new SGUI();
		testGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testGui.setSize(540, 520);
		testGui.setResizable(false);
		testGui.setVisible(true);
		
//		SBoard gameBoard = new SBoard();
//		try {
//			gameBoard.launchGameBoard("Hard");
//		} catch (IOException e) {
//			System.out.println("IOException");
//			e.printStackTrace();
//		}
	}
}