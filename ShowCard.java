import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import java.util.*;
import java.io.*;
import java.lang.*;

import javax.imageio.*;
 
 
public class ShowCard extends JPanel { //will show a particular card. Can move the main method to different class
   static int WindowWidth = 900;
   static int WindowHeight = 700;
   static int x;
   static int y;
   
   static ArrayList<BufferedImage> Deck = new ArrayList<BufferedImage>();
 
   public ShowCard(int xx, int yy){
	   x = xx;
	   y = yy;
      setPreferredSize(new Dimension(WindowWidth,WindowHeight));
      setBackground(new Color(0, 255, 0, 128));
   }
 
  static public void showCards(int xxx, int yyy) {
     JFrame frame = new JFrame("52 Pick-Up");
     Container cnt = frame.getContentPane();
     ShowCard pnl = new ShowCard(xxx, yyy);
     cnt.add(pnl);
     frame.pack();
     frame.setVisible(true);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
  public void paintComponent(Graphics gr){
	  
	  Card a = new Card(3, 8);
	  BufferedImage aa = a.getFace();
	  Card b = new Card(0, 2);
	  BufferedImage bb = b.getFace();
	  Card c = new Card(2, 12);
	  BufferedImage cc = c.getFace();
	  Card d = new Card(0, 13);
	  BufferedImage dd = d.getFace();
	  super.paintComponent(gr);
	  gr.drawImage(aa, 15, 15, (ImageObserver)null);
	  gr.drawImage(bb,  100, 100, (ImageObserver)null);
	  gr.drawImage(cc,  400, 100, (ImageObserver)null);
	  gr.drawImage(dd,  x, y, (ImageObserver)null);
   }
  public static void main (String[] args) throws java.io.IOException {
 	 	Scanner scan = new Scanner(System.in);
		System.out.println("Where would you like one of the card?");
 	 	int x = scan.nextInt();
 	 	int y = scan.nextInt();
		ShowCard.showCards(x, y);
		scan.close();
  }
}