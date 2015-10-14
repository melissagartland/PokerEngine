package MainPackage;


import java.util.Comparator;
import MainPackage.eRank;
import MainPackage.eSuit;

import javax.xml.bind.annotation.XmlElement;

public final class Card{
	private eSuit Suit;
	private eRank Rank;
	private boolean Wild;
	
	private Card(){
	//no arg constructor, card attributes kept private
	}
	//next two methods create instance of Card
	public Card(eSuit suit, eRank rank){
		Suit = suit;
		Rank = rank;
		setWild(false);
	}
	public Card(eSuit suit, eRank rank, int CardNbr){
		Suit = suit;
		Rank = rank;
		this.setWild(false);
	}
	//getter for rank
	public eRank getRank(){
		return this.Rank;
	}
	public eSuit getSuit(){
		return this.Suit;
	}
	public boolean getWild(){
		return this.Wild = true;
	}
	public void setWild(boolean wild) {
		Wild = wild;
	}
	// comparator method for sorting by rank
	public static Comparator<Card> CardRank = new Comparator<Card> (){
		public int compare(Card card1, Card card2){
			int c1 = card1.getRank().getRank();
			int c2 = card2.getRank().getRank();
			//for descending order
			return c2 - c1;
		}
	}; //semi colon for method within a method
	
	
}
