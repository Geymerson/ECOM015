package cards;

import javax.swing.ImageIcon;

public interface Cards {

	public void setName(String name);

	public String getName();
	
	public void setCardIconImage(ImageIcon cardImageIcon);

	public ImageIcon getCardIconImage();
}
