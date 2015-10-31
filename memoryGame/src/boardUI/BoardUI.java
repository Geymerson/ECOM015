package boardUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.Board;

public class BoardUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Board board;
	private JPanel boardPanel;
	private JPanel buttonsPanel;
	private JPanel gamePanel;
	private JLabel[] cardsLabel;
	private ImageIcon cardBackground;
	private boolean lockedBoard;
	private MouseHandler mouseHandler;
	private TimerListener timerHandler;
	private Timer flipDelay;
	private Timer memorizingTimer;
	private int cardIndex;
	private JTextField nameAndScoreField;
	private JTextArea informationField;
	private Font font;
	private JButton[] buttons;
	private CareTaker careTaker;
	private Originator originator;

	public BoardUI() {
		startGame();
	}

	public void startGame() {
		//Memento originator
		originator = new Originator();
		
		//Memento list
		careTaker = new CareTaker();
		
		//set flip and memorizing timer
		timerHandler = new TimerListener();
		flipDelay = new Timer(1000, timerHandler);
		setMemorizingTimer();

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 5, 3, 3));

		//Game and board panels
		gamePanel = new JPanel();

		//Set a 4 x 4 board panel with a spacement of 2 
		//between lines and columns
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(4, 4, 2, 2));

		//Set buttons on the panel
		setButtons();

		font = new Font("Serif", Font.BOLD, 12);

		//Set cards background
		cardBackground = 
				new ImageIcon("src/cardsImage/cardBackground.png");

		//Set cards labels
		cardsLabel = new JLabel[16];

		//Mouse event handler
		mouseHandler = new MouseHandler();

		//Load and start board
		board = new Board();
		board.launchBoard();
		originator.setBoardState(board);

		//Listen to mouse events while
		//the board is not locked
		lockedBoard = false;

		//Set player name on the panel
		setPlayerNameAndScoreField();

		//Set information on the panel
		setInformationField();

		//Display board for 5 seconds
		revealGameBoard();
		memorizingTimer.start();
		
		//Save initial game state
		careTaker.add(originator.saveCardsAndBoardStateToMemento());

		gamePanel.add(boardPanel, BorderLayout.CENTER);
		gamePanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(gamePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 600);
		setResizable(false);
		setVisible(true);
	}
	
	public void revealGameBoard() {

		if(cardsLabel == null) {
			cardsLabel = new JLabel[16];
		}

		if(board == null) {
			board = new Board();
			board.launchBoard();
		}

		for(int i = 0; i < 16; i++) {
			//Set card numbers
			cardsLabel[i] = 
					new JLabel("", 
							board.getCardAt(i).getCardIconImage(),
							JLabel.CENTER);
			boardPanel.add(cardsLabel[i]);
		}
	}

	public void setButtons() {
		buttons = new JButton[5];

		for(int i = 0; i < 5; i++) {
			buttons[i] = new JButton("Button" + i);
			buttonsPanel.add(buttons[i]);
		}
	}

	public void setMemorizingTimer() {
		memorizingTimer = new Timer(5000, new TimerListener(){
			public void actionPerformed(ActionEvent arg0) {

				//Set card numbers
				for(int i = 0; i < 16; i++) {
					cardsLabel[i].setIcon(cardBackground);
					cardsLabel[i].addMouseListener( mouseHandler );
				}
				
				//Save initial cards state
				originator.setCardsState(cardsLabel);
				//unlock board
				lockedBoard = false;
				informationField.append("\nTime is over, you might start now.");
				memorizingTimer.stop();
			}
		});
	}

	public void setPlayerNameAndScoreField() {
		nameAndScoreField = new JTextField();
		nameAndScoreField.setFont(font);
		nameAndScoreField.setText("Hello, Player;\tYour current score is: "+ 0 +" points");
		nameAndScoreField.setEditable(false);
		add(nameAndScoreField, BorderLayout.NORTH);
	}

	public void setInformationField() {
		informationField = new JTextArea(4, 30);
		informationField.setFont(font);
		informationField.setText("You have 5 seconds to memorize...");
		informationField.setEditable(false);
		add(new JScrollPane(informationField), BorderLayout.SOUTH);
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg1) {
			cardsLabel[cardIndex]
					.setIcon(cardBackground);

			cardsLabel[board.getLastClickednumber() - 1]
					.setIcon(cardBackground);
			board.setLastClickedNumber(0);
			lockedBoard = false;
			flipDelay.stop();
			informationField.append("\nThese cards don't match, try again!");
		}
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {

			if(!lockedBoard) {

				for(int i = 0; i < 16; i++) {
					if(cardsLabel[i] == event.getSource()) {
						cardIndex = i;
					}
				}

				cardsLabel[cardIndex]
						.setIcon(board.getCardAt(cardIndex).getCardIconImage());

				if(board.getLastClickednumber() == 0) {

					//Set last clicked card
					board.setLastClickedName(board.getCardAt(cardIndex).getName());
					board.setLastClickedNumber(cardIndex + 1);
					informationField.append("\nChoose one more card.");
				}
				else {
					if(board.getLastClickedName()
							.equals(board.getCardAt(cardIndex).getName())) {

						//Make cards not clickable
						cardsLabel[cardIndex]
								.removeMouseListener(mouseHandler);

						cardsLabel[board.getLastClickednumber() - 1]
								.removeMouseListener(mouseHandler);
						board.setLastClickedNumber(0);
						informationField.append("\nThats a match! Well done.");
					}
					else {
						//Disable click responses and wait 1
						//second to flip cards again
						lockedBoard = true;
						flipDelay.start();
					}
				}
			}
		}//end method mouseClicked
	}//end inner class MouseHandler
}//end class BoardUI