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
	   
	   Hand thisHand = null;
	   Deck thisDeck = null;
	   ArrayList<Card> theCards = new ArrayList<Card>();
	   int round = 0;
	 
	   JButton Hit = new JButton("Hit Me");
	   JButton Bet = new JButton("Bet!");
	   
	   //slider code
	   static final int sliderMin = 0;
	   static final int sliderMax = 100;
	   JSlider bettingSlider = new JSlider(JSlider.HORIZONTAL,100, 0);

	   int betPool;


	   public ShowCard(Hand h, Deck d){
	       
		   thisHand = h;
		   thisDeck = d;
		   theCards = thisHand.getHand(theCards);
		   
	      setPreferredSize(new Dimension(WindowWidth,WindowHeight));
	      setBackground(new Color(0, 255, 0, 128));
	      this.add(Hit);  //adds button so stuff can be done
		  Hit.addActionListener(this);
		  this.add(Bet);
		  Bet.addActionListener(this);

	      //slider setup
	      bettingSlider.setMajorTickSpacing(25);
		  bettingSlider.setMinorTickSpacing(5);
		  bettingSlider.setPaintTicks(true);
		  bettingSlider.setPaintLabels(true);
	      this.add(bettingSlider);


	   }
	   
	 
	  static public void showCards(Hand hh, Deck dd) {
	     JFrame frame = new JFrame("Blackjack!");
	     Container cnt = frame.getContentPane();
	     ShowCard pnl = new ShowCard(hh, dd);
	     cnt.add(pnl);
	     frame.pack();
	     frame.setVisible(true);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	 
	  public void paintComponent(Graphics gr){
		  
		  super.paintComponent(gr);
		  int xx = 50;
		  int yy = 50;
		  
		  ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
		  for (Card s : theCards){
			   cardImages.add(s.getFace());
		   }
		  for (BufferedImage i : cardImages){
			 gr.drawImage(i, xx, yy, (ImageObserver)null);
			 xx = xx+50;
		  }
		  
		  int zz = xx+200;
		  gr.setColor(Color.blue);
		  gr.drawString("Round: " + round + " Your Score is: " + thisHand.checkScore(theCards) + " Your purse: $"+thisHand.getMoney() , 350, zz+5);
	  }
	  
	  public void actionPerformed(ActionEvent e){  //this just makes sure something happens when the button is pressed
			int betCount=0; //to prevent betting more than once per hit
		  if(e.getSource() == Hit && round < 5){
				thisHand.hitMe(thisDeck.drawCard(),theCards);
				round++;
				//natural BlackJack
				if(thisHand.checkScore(theCards)==21){
					thisHand.setMoney(thisHand.getMoney()+((int)1.5*betPool));
				repaint();
				betCount = 0;
			}
			if(e.getSource()==Bet && betCount<1){
				int currentBet = bettingSlider.getValue();
				betPool=+currentBet;
				thisHand.setMoney(thisHand.getMoney() - currentBet);
				
				}
				betCount++;
			}
	  }
	  
	}
