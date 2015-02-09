import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Card {
	int suit;
	int number;
	String xi;
	BufferedImage face;
	
	public Card(int s, int n){
		suit = s;
		number = n;
		
		String ss = "" + getSuit().charAt(0);
		String nn = "" + getStringNumber().charAt(0);
		xi = ss + nn;
		
		try {
			face = ImageIO.read(new File(xi + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getSuit(){
		String whatsuit;
		if (suit == 0) whatsuit = "diamonds";
		else if (suit == 1) whatsuit = "hearts";
		else if (suit == 2) whatsuit = "clubs";
		else whatsuit = "spades";
		return whatsuit;		
	}
	
	public String getStringNumber(){
		String whatnumber;
		if (number > 10){
			if (number == 11) whatnumber = "jack";
			if (number == 12) whatnumber = "queen";
			else whatnumber = "king";
		}
		else whatnumber = "" + number;
		return whatnumber;
	}
	
	public int getNumber(){
		return number;
	}
	
	public BufferedImage getFace(){
		return face;
	}
	
	public String toString(){
		return getNumber() + " of " + getSuit() + " and " + xi;
	}
}

