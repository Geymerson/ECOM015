import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SGUI extends JFrame {
	private GridBagLayout gameLayout;
	private GridBagConstraints constraints;
	private JTextField[] cell;
	private JLabel playerName;
	private JLabel playerScore;
	private JLabel gameRankLabel;
	private JButton solveButton;
	private JButton showSolutionButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton restartButton;
	private JButton newGameButton;
	private JRadioButton easyButton;
	private JRadioButton mediumButton;
	private JRadioButton hardButton;
	private ButtonGroup difficultButtonsGroup;
	private JTable ranking;
	private JScrollPane scrollPane;
	private Font font;
	private SBoard gameBoard;
	private SPlayer player;
	private SOptions gameOptions;
	private SRules gameRules;
	private String difficult;
	private ArrayList<SPlayer> playerList; 
	String[] jTableColumns = {"Name", "Score"};
	String[][] players;
	
	public SGUI() {
		super("Sudoku");
		gameLayout = new GridBagLayout();
		setLayout(gameLayout);
		constraints = new GridBagConstraints();
		font = new Font("SansSerif", Font.BOLD, 20);
		cell = new JTextField[81];
		gameBoard = new SBoard();
		player = new SPlayer();
		gameOptions = new SOptions();
		gameRules = new SRules();
		difficult = "Easy";
		players = new String[10][100];
		
		gameRankLabel = new JLabel("Top Solvers");
		addComponent(gameRankLabel, 2, 11, 2, 1);
		constraints.fill = GridBagConstraints.CENTER;
		
		//Loading player rank
		try {
			playerList = gameOptions.loadRank();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < playerList.size(); i++) {
			String[] temp = {playerList.get(i).getPlayerName(),
					Integer.toString(playerList.get(i).getPlayerScore())};
			players[i] = temp;
		}
		ranking = new JTable(players, jTableColumns);
		ranking.setPreferredScrollableViewportSize(ranking.getPreferredSize());
		scrollPane = new JScrollPane(ranking);
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(scrollPane, 3, 11, 2, 5);
		
		//Setting difficult buttons
		easyButton = new JRadioButton("Easy", true);
		easyButton.addItemListener(new DifficultButtonsHandler("Easy"));
		addComponent(easyButton, 8, 0, 1, 1);
		
		mediumButton = new JRadioButton("Medium", false);
		mediumButton.addItemListener(new DifficultButtonsHandler("Medium"));
		addComponent(mediumButton, 9, 0, 1, 1);
		
		hardButton = new JRadioButton("Hard", false);
		hardButton.addItemListener(new DifficultButtonsHandler("Hard"));
		addComponent(hardButton, 10, 0, 1, 1);
		
		difficultButtonsGroup = new ButtonGroup();
		difficultButtonsGroup.add(easyButton);
		difficultButtonsGroup.add(mediumButton);
		difficultButtonsGroup.add(hardButton);
		
		//Launching game board
		try {
			gameBoard.launchGameBoard(difficult);
			gameBoard.launchPlayerBoard();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Setting player name
		try {
			while(player.getPlayerName().isEmpty()) {
				player.setPlayerName(
						JOptionPane.showInputDialog("Please, tell me your name!"));
			}
		} catch (NullPointerException e) {
			player.setPlayerName("Player");
		}
		//Setting player profile name
		player.setPlayerProfile(player.getPlayerName());
		
		//Fix needed for strings greater than 10,
		//it breaks the layout
		if(player.getPlayerName().length() > 10) {
			playerName = new JLabel(
					player.getPlayerName().substring(0,7)+
					"...");
		}
		else {
			playerName = new JLabel(player.getPlayerName());
		}
		addComponent(playerName, 0, 0, 2, 1);
		
		//Adding graphic interface components
		playerScore = new JLabel("PlayerScore: "+player.getPlayerScore());
		addComponent(playerScore, 12, 0, 2, 1);

		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ButtonHandler());
		addComponent(newGameButton, 8, 11, 1, 1);

		restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ButtonHandler());
		addComponent(restartButton, 9, 11, 1, 1);
		
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(new ButtonHandler());
		addComponent(saveButton, 10, 11, 1, 1);

		loadButton = new JButton("Load Game");
		loadButton.addActionListener(new ButtonHandler());
		addComponent(loadButton, 8, 12, 1, 1);

		showSolutionButton = new JButton("Show Solution");
		showSolutionButton.addActionListener(new ButtonHandler());
		addComponent(showSolutionButton, 9, 12, 1, 1);

		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ButtonHandler());
		addComponent(solveButton, 10, 12, 1, 1);
		
		constraints.fill = GridBagConstraints.NONE;
		int counter = 0;

		for(int row = 2; row < 11; row++) {
			for(int column = 1; column < 10; column ++) {
				cell[counter] = new JTextField();
				cell[counter].setPreferredSize(new Dimension(40, 40));
				cell[counter].setFont(font);
				cell[counter].setHorizontalAlignment(JTextField.CENTER);
				int playerBoardCell =
						gameBoard.getCell(row - 2,
								column - 1,
								gameBoard.getPlayerBoard());
				if(playerBoardCell != 0) {
					cell[counter].setText(Integer.toString(playerBoardCell));
					cell[counter].setEditable(false);
				}
				addComponent(cell[counter], row, column + 1, 1, 1);
				counter++;
			}
		}
	}//End class empty constructor

	//Private method to add graphic components
	private void addComponent(Component component,
			int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		gameLayout.setConstraints(component, constraints);
		add(component);
	}//End method addComponent

	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == solveButton) {
				int atPosition = 0;
				for(int row = 0; row < 9; row++) {
					for(int column = 0; column < 9; column++) {
						if(cell[atPosition].isEditable() &&
								cell[atPosition].getText().length() == 1){
							try {
								int cellValue = 
										Integer.parseInt(cell[atPosition].getText());
								if(cellValue >= 1 && cellValue <= 9) {
									gameBoard.setCell(row,
											column,
											cellValue,
											gameBoard.getPlayerBoard());
								}
								else {
									gameBoard.setCell(row,
											column,
											0,
											gameBoard.getPlayerBoard());
								}
							}catch(NumberFormatException e) {
								gameBoard.setCell(row,
										column,
										0,
										gameBoard.getPlayerBoard());
							}
						}
						atPosition++;
					}
				}
				if(gameRules.validateBoard(gameBoard.getPlayerBoard())) {
					JOptionPane.showMessageDialog(solveButton.getParent(),
							"Congratulations.");
					player.setPlayerScore(player.getPlayerScore() + 10);
					playerScore.setText("Score: "+player.getPlayerScore());
				}
				else {
					JOptionPane.showMessageDialog(solveButton.getParent(),
							"Wrong answer! Try again.");
				}
			}
			else if (event.getSource() == showSolutionButton) {
				int atPosition = 0;
				solveButton.setEnabled(false);
				for(int row = 0; row < 9; row++) {
					for(int column = 0; column < 9; column++) {
						if(cell[atPosition].isEditable()){
							int gameBoardCell =
									gameBoard.getCell(row,
											column,
											gameBoard.getGameBoard());
							cell[atPosition].setText(Integer.toString(gameBoardCell));
							
						}
						atPosition++;
					}
				}
			}
			else if (event.getSource() == restartButton) {
				gameBoard.restartBoard();
				solveButton.setEnabled(true);
				int atPosition = 0;
				for(int row = 0; row < 9; row++) {
					for(int column = 0; column < 9; column++) {
						if(cell[atPosition].isEditable()){
							cell[atPosition].setText("");
						}
						atPosition++;
					}
				}
			}
			else if (event.getSource() == saveButton) {
				try {
					gameOptions.saveGame(player, gameBoard);
					JOptionPane.showMessageDialog(saveButton.getParent(),
							"Game Saved.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (event.getSource() == loadButton) {
				String profileName  =
						JOptionPane.showInputDialog("Inform profile name");
				try {
					gameOptions.loadGame(profileName, player, gameBoard);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (event.getSource() == newGameButton) {
				try {
					gameBoard.newBoard(difficult);
					solveButton.setEnabled(true);
					int atPosition = 0;
					for(int row = 0; row < 9; row++) {
						for(int column = 0; column < 9; column ++) {
							cell[atPosition].setEditable(true);
							cell[atPosition].setText("");
							int playerBoardCell =
									gameBoard.getCell(row,
											column,
											gameBoard.getPlayerBoard());
							if(playerBoardCell != 0) {
								cell[atPosition].setText(
										Integer.toString(playerBoardCell));
								cell[atPosition].setEditable(false);
							}
							atPosition++;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}//End class ButtonHandler
	
	private class DifficultButtonsHandler implements ItemListener {
		//Board difficult level
		private String diffct;
		
		public DifficultButtonsHandler(String difficult) {
			this.diffct = difficult;
		}
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			difficult = diffct;
		}
	}//End inner class DifficultButtonsHandler
}//End class SGUI