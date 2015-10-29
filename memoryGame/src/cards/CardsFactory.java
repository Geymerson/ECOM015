package cards;

import java.util.HashMap;

public class CardsFactory {
	
	public static final HashMap<String, Card> cardMap = new HashMap();
	
	public static Card getCard(String cardName) {
		Card card = (Card) cardMap.get(cardName);
		
		if(card == null) {
			card = new Card(cardName);
			cardMap.put(cardName, card);
		}
		return card;
	}
}
