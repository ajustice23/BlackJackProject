import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card>();
	int i=0;

	public Deck(){
		//ArrayList<Card> deck = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				Card tempCard = new Card(i, j);
				deck.add(tempCard);
			}
		}
		Collections.shuffle(deck);	
	}


	public Card drawCard() {
		
		Card cardDrawn = deck.get(i);
		i++;
		//System.out.println(52-i);
		return cardDrawn;

	}
	
}