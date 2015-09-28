package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import MainPackage.eSuit; //import Enum for suit
import MainPackage.eRank; //import Enum for rank

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck(){
		//public array list to add cards
		ArrayList<Card> MakingDeck = new ArrayList<Card>();
		for (short i = 0; i<= 3; i++){
			eSuit SuitValue = eSuit.values() [i];
			for (short j = 0; j<= 12; j++){
				eRank RankValue = eRank.values() [j];
				Card newCard = new Card(SuitValue, RankValue, (13*i) +j+1);
				MakingDeck.add(newCard);
			}
		}
		cards = MakingDeck; //sync up the array list to add new cards to the card array list
		ShuffleCards();
	}
	
	//method for determining number of jokers in the deck
	public Deck(int numOfJokers){
		this();
		for (short i = 1; i <= numOfJokers; i++){
			cards.add(new Card(eSuit.JOKER, eRank.JOKER, 53));	
		}
		ShuffleCards();
	}
	//determining the wild card
	public Deck(int numOfJokers, ArrayList<Card> WildCards){
		this(numOfJokers);
		for(Card deckCard : cards){ //for each loop
			for(Card WildCard : WildCards){
				if((deckCard.getSuit() == WildCard.getSuit()) &&
						(deckCard.getRank() == WildCard.getRank())){
					deckCard.setWild();
				}
			}
		}
		ShuffleCards();
	}
	
	//method to shuffle cards, calls from the Collections object
	private void ShuffleCards(){
		Collections.shuffle(cards);
	}
	
	//draws first card from deck and returns it
	public Card drawFromDeck(){
		Card FirstCard = cards.get(0);
		cards.remove(0);
		return FirstCard;
	}
	
	public int getTotalCards(){
		return cards.size();
	}
	
	public ArrayList<Card> getCards(){
		return this.cards;
	}
}
