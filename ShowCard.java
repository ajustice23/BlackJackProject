import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;


public class ShowCard extends JPanel implements ActionListener {
	static int WindowWidth = 900;
	static int WindowHeight = 700;

	Hand thisHand = null;
	Dealer thisDealer = null;								//Dealer's hand object
	Deck thisDeck = null;									//hold's the deck being used in the game
	ArrayList<Card> theCards = new ArrayList<Card>();		//hold's the user's hand as a card array
	ArrayList<Card> splitCards = new ArrayList<Card>();     //hold's the split hand as a card array
	ArrayList<Card> dealerHand = new ArrayList<Card>();		//hold's the dealer's hand as a card array
	int round = 0;
	int bettingPool =0;
	boolean splitCheck = false;
	boolean firstBet = false; //makes sure the first bet takes place before the cards are shown
	boolean didYouBet =false; //to prevent betting more than once per hit
	boolean pleaseBet = false; //to tell the user to bet first 
	boolean youWon = false;
	boolean dealerWon=false;
	JButton Hit = new JButton("Hit Me");
	JButton Bet = new JButton("Bet!");
	JButton GiveUp = new JButton ("Hold");
	JButton Split = new JButton ("Split");
	JButton Redeal = new JButton ("Redeal");

	//  JButton Hit1 = new JButton("Hit Me");
	//  JButton Bet1 = new JButton("Bet!");

	//slider code
	static final int sliderMin = 0;
	static final int sliderMax = 100;
	JSlider bettingSlider = new JSlider(JSlider.HORIZONTAL,100, 0);



	public ShowCard(Hand h, Deck d, Dealer p){

		thisHand = h;
		thisDeck = d;
		thisDealer = p;
		theCards = thisHand.getMainHand();
		dealerHand = thisDealer.getMainHand();

		setPreferredSize(new Dimension(WindowWidth,WindowHeight));
		setBackground(new Color(0, 255, 0, 128));
		this.add(Hit);  //adds button so stuff can be done
		Hit.addActionListener(this);
		this.add(Bet);
		Bet.addActionListener(this);
		this.add(GiveUp);
		GiveUp.addActionListener(this);
		this.add(Split);
		Split.addActionListener(this);
		this.add(Redeal);
		Redeal.addActionListener(this);


		//Hit1.setLocation(130, 150);
		//Hit1.setVisible(splitCheck);
		//Bet1.setLocation(250, 150);
		//Bet1.setVisible(splitCheck);
		//this.add(Hit1);
		//Hit1.addActionListener(this);
		//this.add(Bet1);
		//Bet1.addActionListener(this);

		//slider setup
		bettingSlider.setMajorTickSpacing(25);
		bettingSlider.setMinorTickSpacing(5);
		bettingSlider.setPaintTicks(true);
		bettingSlider.setPaintLabels(true);
		this.add(bettingSlider);
	}

	static public void showCards(Hand hh, Deck dd, Dealer pp) {
		JFrame frame = new JFrame("Blackjack!");
		Container cnt = frame.getContentPane();
		ShowCard pnl = new ShowCard(hh, dd, pp);
		cnt.add(pnl);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paintComponent(Graphics gr){
		BufferedImage faceDown = null;
		try {
			faceDown = ImageIO.read(new File("b1fv.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.paintComponent(gr);
		int xx = 50; //hold's the x coordinate of the cards (change's for each card)
		int yy = 50; //hold's the y coordinate of the cards (will not change)

		ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
		for (Card s : theCards){		//displaying cards from user's hand by putting in an array and painting
			cardImages.add(s.getFace());
		}
		if(firstBet == true){
			for (BufferedImage i : cardImages){
					gr.drawImage(i, xx, yy, (ImageObserver)null); // this is where the images in the array actually get put on screen
					xx = xx+50;
			}

		} else{
			
			gr.drawImage(faceDown, xx, yy, (ImageObserver)null); //puts face down cards on the screen before the first bet 
			gr.drawImage(faceDown, xx+50, yy, (ImageObserver)null); 

		}

		xx = 50;
		yy = 200;

		ArrayList<BufferedImage> splitImages = new ArrayList<BufferedImage>(); //using the same method to display dealer's hand
		for (Card q : splitCards) splitImages.add(q.getFace());

		for (BufferedImage r : splitImages){
			gr.drawImage(r, xx, yy, (ImageObserver)null);
			xx = xx+50;
		}

		xx = 50;
		yy = 350;

		ArrayList<BufferedImage> dealercardImage = new ArrayList<BufferedImage>(); //using the same method to display dealer's hand
		for (Card q : dealerHand) dealercardImage.add(q.getFace());

		if(firstBet == true){
		for (BufferedImage r : dealercardImage){
			gr.drawImage(r, xx, yy, (ImageObserver)null);
			xx = xx+50;
			int zz = xx+300;

			gr.setColor(Color.blue); //printing the scores in blue font
			gr.drawString("Round: " + round + " Your Score is: " + thisHand.checkScore(theCards)+ " You have: $"+thisHand.getMoney(), 350, zz+5);
			gr.drawString("Dealer Score: " + thisDealer.checkScore(dealerHand), 350, zz+20);
			}
		}else{
			int zz = xx+350;
			gr.drawImage(faceDown, xx, yy, (ImageObserver)null); //puts face down cards on the screen before the first bet 
			gr.drawImage(faceDown, xx+50, yy, (ImageObserver)null); 
			gr.drawString("Round: " + round + " You have: $"+thisHand.getMoney(), 350, zz+5);

		
		}
		if(didYouBet){//prints amount that the user bet 
			gr.drawString("You bet $"+bettingSlider.getValue(), 500, 200);
		}
		if(pleaseBet){//tells the user to bet first before hitting
			gr.drawString("You must bet before you can hit!", 500, 200);

		}
		if(firstBet && youWon){
			gr.drawString("You Won! Press Redeal if you would like to play again.", 500, 200);
		}
		if(firstBet && dealerWon){
			gr.drawString("You Lost! Press Redeal if you would like to play again.", 500, 200);
		}
	}

	public void actionPerformed(ActionEvent e){  //this just makes sure something happens when the button is pressed
		if(e.getSource() == Hit && round < 5 &&!youWon && !dealerWon){
			
			if(!didYouBet){//to tell the user to bet before hitting
				pleaseBet=true;
				repaint();
			}else{
			thisHand.hitMe(thisDeck.drawCard(),theCards);
			thisDealer.hitMe(thisDeck.drawCard(),dealerHand);
			round++;
			thisHand.checkScore(theCards);
			thisDealer.checkScore(dealerHand);
			dealerWon= thisDealer.didYouWin();
			if(thisHand.checkScore(theCards)>21){
				dealerWon=true;
			}
			youWon=thisHand.didYouWin();
			if(youWon){
				thisHand.setMoney(thisHand.getMoney()+ 1.5*bettingPool);
				dealerWon= false;
			}
			repaint();
			didYouBet = false;
			}
		}
		if(e.getSource()==Bet && !didYouBet && !youWon && !dealerWon){
			int currentBet= bettingSlider.getValue();
			bettingPool+=currentBet;
			thisHand.setMoney(thisHand.getMoney()-currentBet);//subtracts bet from purse
			didYouBet = true;
			if(!firstBet) didYouBet = false; // prevents the user from hitting without betting the first time the cards are flipped
			firstBet =true;
			pleaseBet= false;
			if(firstBet==false){//checks win on first round
				thisHand.checkScore(theCards);
				thisDealer.checkScore(dealerHand);
				dealerWon = thisDealer.didYouWin();
				youWon=thisHand.didYouWin();
			
				if(youWon ){
					thisHand.setMoney(thisHand.getMoney()+(1.5*bettingPool));//returns 1.5 times betting pool for natural black jack
					dealerWon= false;//makes sure you won even if the dealer gets dealt blackjack as well
				}
			}
			repaint();
		}
		if(e.getSource() == GiveUp && !youWon){
			boolean dealerEnd= false;
			while(dealerEnd = false){
				if (thisDealer.getDealerScore() < 17){
					thisDealer.hitMe(thisDeck.drawCard(), dealerHand);
					int score =thisDealer.checkScore(dealerHand);
					if(score>thisHand.checkScore(theCards)) {
						dealerEnd=true;
						break;
						}
					if(score == 21) {
						dealerEnd=true;
						dealerWon=true;
						}
					}
				repaint();
				}
			
			repaint();
		}
		if(e.getSource()==Split ){
			if(theCards.get(0).getNumber()==theCards.get(1).getNumber()){
				thisHand.split(thisDeck.drawCard(), thisDeck.drawCard(), theCards);
				splitCards = thisHand.getsplithand();
				round++;
				splitCheck = true;
				didYouBet= false;
				repaint();
			}			
		}
		boolean gameOver= false;
		if(dealerWon || youWon){
			gameOver=true;
		}
		if(e.getSource()==Redeal && gameOver){
			thisHand.redeal(thisDeck.drawCard(),thisDeck.drawCard());
			thisDealer.redeal(thisDeck.drawCard(),thisDeck.drawCard());
			youWon=false;
			firstBet=false;
			didYouBet=false;
			repaint();
			
		}
	}
}


