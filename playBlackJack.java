public class playBlackJack { //main method
    public static void main (String[] args) throws java.io.IOException{
 	 	
 	 	Deck tryDeck = new Deck();
		Hand currentHand = new Hand();
		tryDeck.makeDeck();
		tryDeck.shuffleDeck();
		currentHand.makeHand();
		currentHand.score();
		currentHand.askUser();
 	 	
 	 	ShowCard.showCards(currentHand.showHandImages());
  }
}
