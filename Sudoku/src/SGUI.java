import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SGUI extends JFrame {
	private GridBagLayout gameLayout;
	private GridBagConstraints constraints;
	private JTextField[] cell;
	private JLabel playerName;
	private JLabel playerScore;
	private JButton solveButton;
	private JButton showSolutionButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton restartButton;
	private JButton newGameButton;
	private Font font;
	private SBoard gameBoard;
	private SPlayer player;
	private SOptions gameOptions;


	public SGUI() {
		super("Sudoku");
		gameLayout = new GridBagLayout();
		setLayout(gameLayout);
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(2,2,2,2);
		font = new Font("SansSerif", Font.BOLD, 20);
		cell = new JTextField[81];
		gameBoard = new SBoard();
		player = new SPlayer();
		gameOptions = new SOptions();
		
		try {
			gameBoard.launchGameBoard("Hard");
			gameBoard.launchPlayerBoard();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(player.getPlayerName().isEmpty()) {
			player.setPlayerName(
					JOptionPane.showInputDialog("Please, tell me your name!"));
			//System.out.println("a" + player.getPlayerName());
		}
		player.setPlayerProfile(player.getPlayerName());
		
		//Fix needed for strings greater than 10
		//it breaker the layout
		if(player.getPlayerName().length() > 10) {
			playerName = new JLabel(
					player.getPlayerName().substring(0,7)+
					"...");
		}
		else {
			playerName = new JLabel(player.getPlayerName());
		}
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(playerName, 0, 0, 1, 1);

		playerScore = new JLabel("PlayerScore: "+player.getPlayerScore());
		//constraints.fill = GridBagConstraints.VERTICAL;
		addComponent(playerScore, 12, 0, 2, 1);

		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ButtonHandler());
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(newGameButton, 5, 11, 1, 1);

		restartButton = new JButton("Restart Game");
		restartButton.addActionListener(new ButtonHandler());
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(restartButton, 6, 11, 1, 1);
		
		saveButton = new JButton("Save Game");
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(saveButton, 7, 11, 1, 1);

		loadButton = new JButton("Load Game");
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(loadButton, 8, 11, 1, 1);

		showSolutionButton = new JButton("Show Solution");
		showSolutionButton.addActionListener(new ButtonHandler());
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(showSolutionButton, 9, 11, 1, 1);

		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ButtonHandler());
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 10, 11, 1, 1);

		int counter = 0;

		for(int row = 2; row < 11; row++) {
			for(int column = 1; column < 10; column ++) {
				cell[counter] = new JTextField();
				cell[counter].setPreferredSize(new Dimension(30, 30));
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
				//cell[counter].setText("1" + counter);
				//cell[counter].setBackground(bg);
				constraints.fill = GridBagConstraints.BOTH;
				addComponent(cell[counter], row, column, 1, 1);
				counter++;
			}
		}
	}

	private void addComponent(Component component,
			int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.weightx = 2;
		constraints.weighty = 2;
		gameLayout.setConstraints(component, constraints);
		add(component);
	}

	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == solveButton) {
				int atPosition = 0;
				for(int row = 0; row < 9; row++) {
					for(int column = 0; column < 9; column++) {
						if(cell[atPosition].isEditable() &&
								cell[atPosition].getText().length() == 1){
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
						}
						atPosition++;
					}
				}
//				JOptionPane.showMessageDialog(solveButton.getParent(),
//						String.format("You pressed %s", event.getActionCommand()));
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
					gameBoard.newBoard("Hard");
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
	}
}