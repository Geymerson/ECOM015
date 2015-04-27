import javax.swing.JFrame;

public class SInit {
	public static void main(String[] args) {
		SGUI sLaunch = new SGUI();
		sLaunch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sLaunch.setSize(750, 440);
		sLaunch.setResizable(false);
		sLaunch.setVisible(true);
	}
}