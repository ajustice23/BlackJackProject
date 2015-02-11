import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();
	
	int count;
	int score;
	
	boolean forfeit = false;

	public Hand(Card a, Card b){
		hand.add(a);
		hand.add(b);
		System.out.println("score: "+checkScore());
		}
	
	public ArrayList<Card> getHand(){ //shows hand
		System.out.println(hand);
		return hand;
	}
	
	public String toString(){
		System.out.println("Your cards are:");
		for(Card c:hand){
			System.out.println(c.toString());
		}
		return null;
	}
	

	public void hitMe(Card c){  //adds a card to the hand arraylist
		hand.add(c);
		toString();
		System.out.println("Your points are: " + checkScore());
		
	}
	
	public int checkScore(){
		int ace=0;
		score=0;
		for (Card cardvalue: hand){  //adds up points in hand
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