import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import java.awt.Component;
import java.io.IOException;

public class SGUI extends JFrame {
	private GridBagLayout layout;
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
	

	public SGUI() {
		super("Sudoku");
		layout = new GridBagLayout();
		setLayout(layout);
		font = new Font("SansSerif", Font.BOLD, 20);
		cell = new JTextField[81];
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(2,2,2,2);
		gameBoard = new SBoard();
		
		try {
			gameBoard.launchGameBoard("Hard");
			gameBoard.launchPlayerBoard();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		playerName = new JLabel("PlayerName");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(playerName, 0, 0, 2, 1);
		
		playerScore = new JLabel("PlayerScore");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(playerScore, 1, 0, 2, 1);
		
		int counter = 0;
		
		for(int row = 2; row < 11; row++) {
			for(int column = 0; column < 9; column ++) {
				cell[counter] = new JTextField();
				cell[counter].setPreferredSize(new Dimension(40, 40));
				cell[counter].setFont(font);
				cell[counter].setHorizontalAlignment(JTextField.CENTER);
				cell[counter].getDocument().addDocumentListener(
						new MyDocumentListener());
				int playerBoardCell =
						gameBoard.getCell(row - 2,
								column,
								gameBoard.getPlayerBoard());
				if(playerBoardCell != 0) {
					//System.out.printf("%d\n", playerBoardCell);
					cell[counter].setText(Integer.toString(playerBoardCell));
					cell[counter].setEditable(false);
				}
				//cell[counter].setText("1" + counter);
				//cell[counter].setBackground(bg);
				//constraints.fill = GridBagConstraints.BOTH;
				addComponent(cell[counter], row, column, 1, 1);
				counter++;
			}
		}
		
		newGameButton = new JButton("New Game");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(newGameButton, 6, 11, 1, 1);
		
		restartButton = new JButton("Restart Game");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(restartButton, 7, 11, 1, 1);
		
		loadButton = new JButton("Load Game");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(loadButton, 8, 11, 1, 1);
				
		showSolutionButton = new JButton("Show Solution");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(showSolutionButton, 9, 11, 1, 1);
		
		solveButton = new JButton("Solve");
		//constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 10, 11, 1, 1);
		
	}
	
	private void addComponent(Component component,
			int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.weightx = 2;
		constraints.weighty = 2;
		layout.setConstraints(component, constraints);
		add(component);
	}
	
	private class MyDocumentListener implements DocumentListener {
		public void changedUpdate(DocumentEvent event) {
			//Empty method
		}
		public void insertUpdate(DocumentEvent event) {
			String temp;
			int value;
			try {
				//Get source of the event and
				//get sources' string of size 1 (first char)
				temp = event.getDocument().getText(0, 1);
				value = Integer.parseInt(temp);
				if(value >= 1 && value <= 9) {
					//TODO
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
	    }
	    public void removeUpdate(DocumentEvent event) {
	        //Empty method
	    }
	}
}