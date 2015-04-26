import java.io.IOException;

import javax.swing.JFrame;

public class SudokuTest {
	public static void main(String[] args) {
		SGUI testGui = new SGUI();
		testGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testGui.setSize(750, 440);
		testGui.setResizable(false);
		testGui.setVisible(true);
		
//		SPlayer player = new SPlayer();
//		player.setPlayerName("fulano");
//		SBoard gameBoard = new SBoard();
//		SOptions options = new SOptions();
		
//		try {
//			gameBoard.launchGameBoard("Hard");
//			gameBoard.launchPlayerBoard();
//		} catch (IOException e) {
//			System.out.println("IOException");
//			e.printStackTrace();
//		}
////		
//		try {
//			options.loadGame("fulano", player, gameBoard);
//			//options.saveGame(player, gameBoard);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		SRules boardVerifier = new SRules();
//		
//		if(!boardVerifier.validateBoard(gameBoard.getPlayerBoard())) {
//			System.out.println("Try again!!!");
//		}
	}
}