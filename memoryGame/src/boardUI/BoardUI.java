package boardUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import javax.swing.text.DefaultCaret;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.BoardFactory;
import board.NormalBoard;
import player.Player;

public class BoardUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel boardPanel;
	private JPanel buttonsPanel;
	private JPanel gamePanel;
	private JLabel[] cardsLabel;
	private JTextField nameAndScoreField;
	private JTextArea informationField;
	private JButton[] buttons;

	private MouseHandler mouseHandler;
	private TimerListener timerHandler;
	private Timer flipDelay;
	private Timer memorizingTimer;
	private Font font;
	private int cardIndex;

	private BoardFactory boardFactory;
	private NormalBoard board;
	private MemorizingState memorizingState;
	private StartingState startingState;
	private Context context;
	private DefaultCaret caret;
	private Player player;
	private boolean lockedBoard;

	public BoardUI() {
		askPlayerInfo();
		startGame();
	}

	public void startGame() {

		//Start game states
		context = new Context();
		memorizingState = new MemorizingState();
		startingState = new StartingState();

		//Load and start board
		boardFactory = new BoardFactory();
		board = (NormalBoard) boardFactory.getBoard("normal");
		board.launchBoard();

		//Unlock board so the player
		//can click
		lockedBoard = false;

		//set flip and memorizing timer
		timerHandler = new TimerListener();
		flipDelay = new Timer(1000, timerHandler);
		setMemorizingTimer();

		//Set buttons panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 5, 2, 2));

		//Game and board panels
		gamePanel = new JPanel();

		//Set a 4 x 4 board panel with a spacement of 2 
		//between lines and columns
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(4, 4, 2, 2));

		//Set buttons on the panel
		setButtons();

		//Set font
		font = new Font("Serif", Font.BOLD, 12);

		//Set cards labels
		cardsLabel = new JLabel[16];

		//Mouse event handler
		mouseHandler = new MouseHandler();

		//Set player name on the panel
		setPlayerNameAndScoreField();

		//Set information on the panel
		setInformationField();

		//Set memorizing state
		memorizingState.updateState(context);
		context.getState().updateTextArea(informationField);
		context.getState().updateBoard(board, cardsLabel, boardPanel);

		//Display board for 5 seconds
		memorizingTimer.start();

		//Configure game graphical interface
		gamePanel.add(boardPanel, BorderLayout.CENTER);
		gamePanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(gamePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 610);
		setResizable(false);
		setVisible(true);
	}

	public void askPlayerInfo() {
		player = new Player(JOptionPane.showInputDialog("Player name:"));
	}

	public void setButtons() {
		buttons = new JButton[5];

		for(int i = 0; i < 5; i++) {

			if(i == 1 || i == 3) {
				if(i == 1) {
					buttons[i] = new JButton("New Game");
				}
				else if(i == 3) {
					buttons[i] = new JButton("Restart");
				}
				buttons[i].addActionListener(new ButtonHandler());
				buttons[i].setEnabled(false);
			}

			else {
				buttons[i] = new JButton("Button" + i);
				buttons[i].setVisible(false);
			}
			buttonsPanel.add(buttons[i]);
		}
	}

	public void setMemorizingTimer() {
		memorizingTimer = new Timer(5000, new TimerListener(){
			public void actionPerformed(ActionEvent arg0) {

				startingState.updateState(context);
				context.getState().updateBoard(board, cardsLabel, mouseHandler);

				//Enable buttons
				buttons[1].setEnabled(true);
				buttons[3].setEnabled(true);

				//unlock board
				lockedBoard = false;

				context.getState().updateTextArea(informationField);
				memorizingTimer.stop();
			}
		});
	}

	public void setPlayerNameAndScoreField() {
		nameAndScoreField = new JTextField();
		nameAndScoreField.setFont(font);
		updatePlayerNameAndScoreField();
		nameAndScoreField.setEditable(false);
		add(nameAndScoreField, BorderLayout.NORTH);
	}

	public void updatePlayerNameAndScoreField() {
		nameAndScoreField.setText("Hello, " + player.getName() +
				";\tYour current score is: "+ player.getPlayerScore() +" points");
	}

	public void setInformationField() {

		informationField = new JTextArea(4, 30);
		informationField.setFont(font);
		informationField.setEditable(false);
		add(new JScrollPane(informationField), BorderLayout.SOUTH);

		//Set auto-scrolling
		caret = (DefaultCaret)informationField.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg1) {

			//Flip down cards
			cardsLabel[cardIndex]
					.setIcon(board.getCardBackground().getCardIconImage());

			cardsLabel[board.getLastClickednumber() - 1]
					.setIcon(board.getCardBackground().getCardIconImage());

			board.setLastClickedNumber(0);

			//Unlock board
			lockedBoard = false;
			flipDelay.stop();
			informationField.append("\nThese cards don't match, try again!");
		}
	}

	private class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			player.setPlayerScore(0);
			updatePlayerNameAndScoreField();

			if(event.getSource() == buttons[1]) {

				informationField.append("\nYou have 5 seconds to memorize.");
				lockedBoard = true;
				buttons[1].setEnabled(false);
				buttons[3].setEnabled(false);
				board.restartBoard();

				for(int i = 0; i < 16; i++) {
					cardsLabel[i].removeMouseListener(mouseHandler);
					cardsLabel[i].setIcon(board.getCardAt(i).getCardIconImage());
				}

				memorizingTimer.start();
			}
			else {
				informationField.append("\nYou might start now.");
				startingState.updateState(context);
				context.getState().updateBoard(board, cardsLabel, mouseHandler);
			}
		}
	}

	protected class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {

			if(!lockedBoard) {

				//Get clicked card number
				for(int i = 0; i < 16; i++) {
					if(cardsLabel[i] == event.getSource()) {
						cardIndex = i;
					}
				}

				//Display clicked card
				cardsLabel[cardIndex]
						.setIcon(board.getCardAt(cardIndex).getCardIconImage());

				if(board.getLastClickednumber() == 0) {

					//Set last clicked card
					board.setLastClickedName(board.getCardAt(cardIndex).getName());
					board.setLastClickedNumber(cardIndex + 1);
					informationField.append("\nChoose one more card.");
				}
				else {
					//If clicked cards match
					if(board.getLastClickedName()
							.equals(board.getCardAt(cardIndex).getName())) {

						//Make cards not clickable
						cardsLabel[cardIndex]
								.removeMouseListener(mouseHandler);

						cardsLabel[board.getLastClickednumber() - 1]
								.removeMouseListener(mouseHandler);

						board.setLastClickedNumber(0);

						//Increase player score
						player.setPlayerScore(
								player.getPlayerScore() + 100);

						//Update player score
						updatePlayerNameAndScoreField();
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