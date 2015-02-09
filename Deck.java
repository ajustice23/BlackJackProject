import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	ArrayList<Card> deck = new ArrayList<Card>();

	public void makeDeck() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				Card tempCard = new Card(i, j);
				deck.add(tempCard);
			}
		}
		shuffleDeck();
		System.out.println(deck.size());

		//System.out.println(deck);
	}

	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	public Card drawCard() {
		Card cardDrawn = deck.get(0);
		deck.remove(0);
		System.out.println(deck.size());
		return cardDrawn;

	}

	public static void main(String[] arg) {
		Deck tryDeck = new Deck();
		tryDeck.makeDeck();
		tryDeck.drawCard();
	}
}