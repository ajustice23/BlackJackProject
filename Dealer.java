import java.util.ArrayList;


public class Dealer extends Hand{
	ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	public Dealer(Card a, Card b) {
		super(a, b);
		// TODO Auto-generated constructor stub
	}
	
	public int goDealer(Deck d){
		int ace = 0;
		int score = 0;
		
		for (Card cardvalue: dealerHand){  //adds up points in hand
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
		}
		else if (ace > 0 && score <=17){//hits on 17 if live ace is in hand
			super.hitMe(d.drawCard(), dealerHand);
			count++;//counts hits
			}
		else if (score<17){// hits if less than 17
			super.hitMe(d.drawCard(), dealerHand);
			count++;//counts hits
		}
		
		return score;
	}
}


//keep in mind, face down card
