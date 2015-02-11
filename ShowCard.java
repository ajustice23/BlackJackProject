import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import javax.swing.*;

import java.util.*;
import java.io.*;
import java.lang.*;

import javax.imageio.*;
 
 
public class ShowCard extends JPanel implements ActionListener{
	   static int WindowWidth = 900;
	   static int WindowHeight = 700;
	   
	   Hand thisHand = null;
	   ArrayList<Card> theCards = new ArrayList<Card>();
	   static ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
	 
	   JButton Play = new JButton("Play");
	   JTextField input = new JTextField(7);  //text field to hold user input
	   
	   public ShowCard(Hand h){
	       
		   thisHand = h;
		   theCards = thisHand.getHand();
		   
		   for (Card s : theCards){
			   cardImages.add(s.getFace());
		   }
		   
	      setPreferredSize(new Dimension(WindowWidth,WindowHeight));
	      setBackground(new Color(0, 255, 0, 128));
	      
	      this.add(Play);  //adds button so stuff can be done
		    Play.addActionListener(this);
	   }
	 
	  static public void showCards(Hand hh) {
	     JFrame frame = new JFrame("Blackjack!");
	     Container cnt = frame.getContentPane();
	     ShowCard pnl = new ShowCard(hh);
	     cnt.add(pnl);
	     frame.pack();
	     frame.setVisible(true);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	 
	  public void paintComponent(Graphics gr){
		  
		  super.paintComponent(gr);
		  int xx = 50;
		  int yy = 50;
		  for (BufferedImage i : cardImages){
			 gr.drawImage(i, xx, yy, (ImageObserver)null);
			 xx = xx+100;
		  }
	  }
	  
	  public void actionPerformed(ActionEvent e){  //this just makes sure something happens when the button is pressed
			if(e.getSource() == Play){
				repaint();
			}
	  }
	}
