import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;
 
 
public class ShowCard extends JPanel implements ActionListener {
	   static int WindowWidth = 900;
	   static int WindowHeight = 700;
	   
	   Hand thisHand = null;									//User's hand object
	   Dealer thisDealer = null;								//Dealer's hand object
	   Deck thisDeck = null;									//hold's the deck being used in the game
	   ArrayList<Card> theCards = new ArrayList<Card>();		//hold's the user's hand as a card array
	   ArrayList<Card> dealerHand = new ArrayList<Card>();		//hold's the dealer's hand as a card array
	   int round = 0;
	 
	   JButton Hit = new JButton("Hit Me");
	   JButton Bet = new JButton("Bet!");
	   JButton GiveUp = new JButton ("Give Up");
	   
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
		  
		  super.paintComponent(gr);
		  int xx = 50; //hold's the x coordinate of the cards (change's for each card)
		  int yy = 50; //hold's the y coordinate of the cards (will not change)
		  
		  ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
		  for (Card s : theCards){		//displaying cards from user's hand by putting in an array and painting
			   cardImages.add(s.getFace());
		   }
		  for (BufferedImage i : cardImages){
			 gr.drawImage(i, xx, yy, (ImageObserver)null); // this is where the images in the array actually get put on screen
			 xx = xx+50;
		  }
		  
		  xx = 50;
		  yy = 200;

		  ArrayList<BufferedImage> dealercardImage = new ArrayList<BufferedImage>(); //using the same method to display dealer's hand
		  for (Card q : dealerHand) dealercardImage.add(q.getFace());
		  
		  for (BufferedImage r : dealercardImage){
			  gr.drawImage(r, xx, yy, (ImageObserver)null);
			  xx = xx+50;
		  }
		  
		  int zz = xx+300;
		  
		  gr.setColor(Color.blue); //printing the scores in blue font
		  gr.drawString("Round: " + round + " Your Score is: " + thisHand.checkScore(theCards), 350, zz+5);
		  gr.drawString("Dealer Score: " + thisDealer.checkScore(dealerHand), 350, zz+20);
	  }
	  
	  public void actionPerformed(ActionEvent e){  //this just makes sure something happens when the button is pressed
			int betCount=0; //to prevent betting more than once per hit
		  if(e.getSource() == Hit && round < 5){
				thisHand.hitMe(thisDeck.drawCard(),theCards);
				round++;
				repaint();
				betCount = 0;
			}
		  if(e.getSource()==Bet && betCount<1){
				bettingSlider.getValue();
				betCount++;
			}
		  if(e.getSource() == GiveUp){
			  /*ideally this button will initiate a dealer method that will automatically go through the dealer's turns
			   * the drawing a card if score is less than 17, namely
			   * though it would be cool if it also did the ace logic
			   * 
			   * so far all it does is add a card to the dealer's hand when it is pressed
			   * this is not actually what it should be doing
			   * but it proves the dealer's hand can be shown and interacted with through buttons
			   */
			  if (thisDealer.getDealerScore() < 21){
				  	thisDealer.hitMe(thisDeck.drawCard(), dealerHand);
			  }
			  repaint();
		  }
			
	  }
	  
	}
