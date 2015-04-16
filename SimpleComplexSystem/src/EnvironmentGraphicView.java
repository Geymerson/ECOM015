import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnvironmentGraphicView extends JApplet {
	private JPanel panel;
	private JLabel [] buttonLabel;
	//private JLabel statusBar;
	private JButton [] button;
	private JTextField [] textField;
	private MonitoredEnvironment monitoredEnvironment;
	private MonitoringEnvironment monitoringEnvironment;
	
	public void init() {
		panel = new JPanel();
		button = new JButton[5];
		buttonLabel = new JLabel[5];
		textField = new JTextField[5];
		//statusBar = new JLabel();
		
		//Setting JLabels' texts
		buttonLabel[0].setText("Monitored environment name");
		buttonLabel[1].setText("Monitoring environment name");
		buttonLabel[2].setText("Temperature");
		buttonLabel[3].setText("Gas outflow");
		buttonLabel[4].setText("Water outflow");
		
		//Setting JButtons' texts
		for(int i = 0; i < 5; i++) {
			button[i].setText("Confirm");
		}
		
		//Connecting JButtons and JTextFields
		button[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField[0].getText() != "") {
					monitoredEnvironment.
					setEnvironmentName(textField[0].getText());
				}
			}
		});
		
		button[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField[1].getText() != "") {
					monitoringEnvironment.
					setEnvironmentName(textField[1].getText());
				}
			}
		});
		
		button[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField[2].getText() != "") {
					monitoredEnvironment.
					setWaterOutflow(Integer.parseInt(textField[2].getText()));
				}
			}
		});

		button[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField[3].getText() != "") {
					monitoredEnvironment.
					setGasOutflow(Integer.parseInt(textField[3].getText()));
				}
			}
		});
		
		button[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField[4].getText() != "") {
					monitoredEnvironment.
					setTemperature(Integer.parseInt(textField[4].getText()));
				}
			}
		});
	}//End method init
}//End class EnvironmentGraphicView