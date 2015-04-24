import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Component;


public class SGUI extends JFrame {
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JTextField[] cell;
	private JLabel playerName;
	private JButton solveButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton restartButton;
	private JButton newGameButton;

	public SGUI() {
		super("Sudoku");
		layout = new GridBagLayout();
		setLayout(layout);
		cell = new JTextField[81];
		constraints = new GridBagConstraints();
		int counter = 0;
		
		for(int row = 0; row < 9; row++) {
			for(int column = 0; column < 9; column ++) {
				cell[counter] = new JTextField();
				constraints.fill = GridBagConstraints.BOTH;
				addComponent(cell[counter], row, column, 1, 1);
				counter++;
			}
		}
		
		solveButton = new JButton("Solve");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 4, 11, 1, 1);
		
		solveButton = new JButton("Show Solution");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 5, 11, 1, 1);
		
		solveButton = new JButton("Restart Game");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 6, 11, 1, 1);
		
		solveButton = new JButton("New Game");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 7, 11, 1, 1);
		
		solveButton = new JButton("Load");
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(solveButton, 8, 11, 1, 1);
		
	}
	
	private void addComponent(Component component,
			int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.weightx = 10;
		constraints.weighty = 10;
		layout.setConstraints(component, constraints);
		add(component);
	}	
}