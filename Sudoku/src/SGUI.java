import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class SGUI extends JFrame {
	private JTextField[] cell;
	private JLabel playerName;
	private JButton solveButton;
	private JButton saveButton;
	private JButton RestartButton;
	
	SGUI() {
		super("Sudoku");
		cell = new JTextField[81];
	}
	
	
}