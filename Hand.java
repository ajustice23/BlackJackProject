import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
	Deck deck = new Deck();
	ArrayList<Card> hand = new ArrayList<Card>();

	boolean forfeit = false;

	public void makeHand(){ //Makes hand
		hand.add(deck.drawCard());
		hand.add(deck.drawCard());
		System.out.println("Your hand is: " + hand);
		System.out.println("Your points are: " + score());
	}


	public void showHand(){ //shows hand
		System.out.println(hand);
	}

	public void splitHand(){ //splits hand
		for(int i = 0; i < 2; i++){
			
		}
	}

	public void hitMe(){  //adds a card to the hand arraylist
		hand.add(deck.drawCard());
		System.out.println(hand);
		System.out.println("Your points are: " + score());
		checkPoints();
	}

	public int score(){ //returns a value of points
		int points = 0;
		boolean ace = false;
		for (Card cardvalue: hand){  //adds up points in hand
			if (cardvalue.getNumber() > 10){ //checks if card is a face card and adds 10
				points = points + 10;
			}
			else { //otherwise adds card value
				points = points + cardvalue.getNumber();
				if (cardvalue.getNumber() == 1){ //if card is an ace adds 10
					points = points + 10;
					ace = true;
				}
			}
		} 
		if (ace == true && points > 21){ //if it was an ace and points are over 21, subtracts the varying ace points
				points = points - 10;
				ace = false;
			}
		return points;
	}

	public void checkPoints(){ //checks points
		int checkpoints = score();
		if (checkpoints > 21){ //are they over 21?
			System.out.println("You busted!");
			giveUp();
		}
		else if (checkpoints == 21) { //if you got blackjack
			System.out.println("Blackjack! You Win!");
		}
		else {
			askUser();
		}
	}

	public void askUser(){ //asks user input
		//checkPoints();
		System.out.println("What do you want to do? Input an answer as lower case hit/showhand/split/quit");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		if (answer.equals("hit")){
			hitMe();
		}
		else if (answer.equals("showhand")){
			showHand();
			askUser();
		}
		else if (answer.equals("split")){
			splitHand();
			askUser();
		}
		else if (answer.equals("quit")){
			forfeit = true;
			giveUp();
		}
		input.close();
	}


	public void giveUp(){ //ends the game
		System.out.println("The game is over!");
		if (score() > 21 || forfeit == true){
			System.out.println("You lost!");
		}
		else {
			System.out.println("You won!");
	}
	}
}