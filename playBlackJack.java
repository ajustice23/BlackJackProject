public class playBlackJack { //main method
    public static void main (String[] args) throws java.io.IOException{
 	 	
 	 	Deck tryDeck = new Deck();
		Hand currentHand = new Hand(tryDeck.drawCard(),tryDeck.drawCard());
		//currentHand.toString();
		Hand currentDealer = new Hand(tryDeck.drawCard(), tryDeck.drawCard());
		
 	 	currentDealer.toString();
 	 	ShowCard.showCards(currentHand, tryDeck, currentDealer);
  }
}

