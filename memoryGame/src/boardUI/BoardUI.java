package boardUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;

public class BoardUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Board board;
	private JPanel boardPanel;
	private JPanel gamePanel;
	private JLabel[] cardsLabel;
	private ImageIcon cardBackground;
	private MouseHandler mouseHandler;
	private boolean lockedBoard;

	public BoardUI() {
		//Cards 
		cardBackground = new ImageIcon("src/cardsImage/cardBackground.png");

		cardsLabel = new JLabel[16];

		gamePanel = new JPanel();

		boardPanel = new JPanel();

		mouseHandler = new MouseHandler();

		lockedBoard = false;
		
		//Set a 4 x 4 board panel with a spacement of 2 
		//between lines and columns
		boardPanel.setLayout(new GridLayout(4, 4, 2, 2));

		//Load and start board
		board = new Board();
		board.launchBoard();

		for(int i = 0; i < 16; i++) {
			//Set card numbers
			cardsLabel[i] = 
					new JLabel("", 
							cardBackground,
							JLabel.CENTER);
			cardsLabel[i].addMouseListener( mouseHandler );
			boardPanel.add(cardsLabel[i]);
		}

		gamePanel.add(boardPanel, BorderLayout.CENTER);
		add(gamePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(510, 510);
		setResizable(false);
		setVisible(true);
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {

			if(!lockedBoard) {

				for(int i = 0; i < 16; i++) {
					if(cardsLabel[i] == event.getSource()) {

						//Display card
						cardsLabel[i].setIcon(board.getCardAt(i).getCardIconImage());
						
						if(board.getLastClickednumber() == 0) {

							//Get last clicked card number and name
							board.setLastClickedName(board.getCardAt(i).getName());
							board.setLastClickedNumber(i + 1);
							System.out.println("1");
						}
						else if(board.getLastClickednumber() - 1 != i){
							
							//If cards names matches
							if(board.getCardAt(i).getName().equals(board.getLastClickedName())) {
								
								//Disable clicking on matching cards
								cardsLabel[board.getLastClickednumber() - 1]
										.removeMouseListener( mouseHandler );
								cardsLabel[i]
										.removeMouseListener( mouseHandler );

								board.setLastClickedName("");
								board.setLastClickedNumber(0);
								System.out.println("2");
							}
							else {							
								cardsLabel[board.getLastClickednumber() - 1]
										.setIcon(cardBackground);

								cardsLabel[i]
										.setIcon(cardBackground);

								//Get last clicked card number and name
								board.setLastClickedName("");
								board.setLastClickedNumber(0);
								System.out.println("3");
							}
						}
					}//end codition if
				}//end loop
			}//end condition if
		}//end method mouseClicked
	}//end inner class MouseHandler
}//end class BoardUI