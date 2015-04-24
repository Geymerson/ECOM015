import javax.swing.JFrame;

public class SudokuTest {
	public static void main(String[] args) {
		SGUI testGui = new SGUI();
		testGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testGui.setSize(500, 400);
		testGui.setVisible(true);
	}
}