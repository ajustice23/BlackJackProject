import java.util.ArrayList;

public class Hand {
	ArrayList<ArrayList> allhands = new ArrayList<ArrayList>();
	int count;
	int score;

	boolean forfeit = false;

	public Hand(Card a, Card b){
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(a);
		hand.add(b);
		allhands.add(hand);
		System.out.println("score: "+checkScore(hand));
	}
	
	

	public ArrayList<Card> getHand(ArrayList<Card> specifichand){ //shows hand
		System.out.println(specifichand);
		return specifichand;
	}

	public String toString(ArrayList<Card> specifichand){
		System.out.println("Your cards are:");
		for(Card c: specifichand){
			System.out.println(c.toString());
		}
		return null;
	}

	public void hitMe(Card c, ArrayList<Card> specifichand){  //adds a card to the hand arraylist
		specifichand.add(c);
		toString();
		System.out.println("Your points are: " + checkScore(specifichand));
	}

	public void split(ArrayList<Card> handtosplit){  //Splits the arraylist of coice 
		Deck newhand = new Deck();
		ArrayList<Card> splithand = new ArrayList<Card>();
		if(handtosplit.get(0).getNumber()==handtosplit.get(1).getNumber()){ //Checks to see if cards in hand are equal
			splithand.add((Card) handtosplit.get(0)); //adds a card from the hand to splithand
			handtosplit.remove(0); //removes the card added to splithand
			splithand.add(newhand.drawCard()); //adds a card to our new split hand
			handtosplit.add(newhand.drawCard());//adds a card to our hand
		}
		allhands.add(splithand);
	}

	public int checkScore(ArrayList<Card> handtocheckscore){
		int ace=0;
		score=0;
		for (Card cardvalue: handtocheckscore){  //adds up points in hand
			if (cardvalue.getNumber() > 10){ //checks if card is a face card and adds 10
				score += 10;
			}
			else { //otherwise adds card value
				score += cardvalue.getNumber();
				if (cardvalue.getNumber() == 1){ //if card is an ace adds 10
					score += 10;
					ace++;//increase ace count by one
				}
			}
		} 
		if (ace > 0 && score > 21){ //ace  anti bust logic
			score -= 10;
			ace-=1;// removes 1 ace from ace pool
		}else if (score<=21 && count==5)// game winning logic by card count
			System.out.println("You win by getting 5 cards!");
		else if(score>21)// game winning logic by dealer bust
			System.out.println("You bust!");
		return score;
	}
}
