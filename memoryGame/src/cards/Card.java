package cards;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card implements Cards {
	
	//Card name
	private String name;
	
	private String imagePath = "src/cardsImage/";
	
	private ImageIcon cardIconImage;
	
	public Card(String name) {
		this.setName(name);
		imagePath = imagePath + name;
		cardIconImage = new ImageIcon(imagePath);
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
