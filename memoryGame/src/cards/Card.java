package cards;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card implements Cards {
	
	//Card name
	private String name;
	
	private String imagePath = "src/cardsImage/";
	
	private ImageIcon cardIconImage;
	
	private JLabel cardLabel;
	
	public Card(String name) {
		this.setName(name);
		imagePath = imagePath + name;
		cardIconImage = new ImageIcon(imagePath);
		cardLabel = new JLabel("", cardIconImage, JLabel.CENTER);
	}

	public void setName(String name) {
		if(name != "") {
			this.name = name;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCardIconImage(ImageIcon cardImageIcon) {
		this.cardIconImage = cardImageIcon;
	}
	
	public ImageIcon getCardIconImage() {
		return this.cardIconImage;
	}
}
