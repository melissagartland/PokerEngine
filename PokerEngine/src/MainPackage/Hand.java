package MainPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

//import javax.xml.bind.annotation.XmlElement;

import MainPackage.eCardNum;
import MainPackage.eStrength;
import MainPackage.eRank;

public class Hand {
	private UUID playerID;
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;
	
	private int HandStrength;
	
	private int HighHand;
	
	private int LowHand;

	private int Kicker;
	
	private boolean bScored = false;
	
	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dJoker = new Deck();
	
	public Hand(){
		
	}
	public void AddCardToHand(Card c){
		if (this.CardsInHand == null){
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	public Card GetCardFromHand(int location){
		return CardsInHand.get(location);
	}
	public Hand(Deck d){
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x<5; x++){
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;	
	}
	public Hand(ArrayList<Card> setCards){
		this.CardsInHand = setCards;
	}
	public ArrayList<Card> getCards(){
		return CardsInHand;
	}
	public ArrayList<Card> getBestHand(){
		return BestCardsInHand;
	}
	public void setPlayerID(UUID playerID){
		this.playerID = playerID;
	}
	public UUID getPlayerID(){
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand){
		this.BestCardsInHand = BestHand;
	}
	public int getHandStrength(){
		return HandStrength;
	}
	public int getKicker(){
		return Kicker;
	}
	public int getHighPairStrength(){
		return HighHand;
	}
	public int getLowPairStrenght(){
		return LowHand;
	}
	public boolean getAce(){
		return Ace;
	}
	public static Hand EvalHand(ArrayList<Card> SeededHand){
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;
		return h;
	}
	public void EvalHand(){
		Collections.sort(CardsInHand, Card.CardRank);
		// Ace Evaluation
		if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank() == eRank.ACE){
			Ace = true;
		}
		//Flush Evaluation
		if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getSuit() == 
				CardsInHand.get(eCardNum.SecondCard.getCardNum()).getSuit()
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getSuit() ==
				CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getSuit()
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getSuit() ==
				CardsInHand.get(eCardNum.FourthCard.getCardNum()).getSuit() 
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getSuit() ==
				CardsInHand.get(eCardNum.FifthCard.getCardNum()).getSuit()){
			Flush = true;
		}
		else {
			Flush = false;
		}
		//Five of a Kind Evaluation
		if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank() == 
				CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank()){
			ScoreHand(eStrength.FiveOfAKind,
					CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank(),0,0);
		}
		//Straight Evaluation
		else if (Ace){
			if (CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank()==eRank.KING
					&& CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank()== eRank.QUEEN
					&& CardsInHand.get(eCardNum.FourthCard.getCardNum()).getRank()== eRank.JACK
					&& CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank()== eRank.TEN){
				Straight = true;
			}
		//Look for Ace 2,3,4,5
			else if (CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank()== eRank.TWO
					&& CardsInHand.get(eCardNum.FourthCard.getCardNum()).getRank()== eRank.THREE
					&& CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank()== eRank.FOUR
					&& CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank()== eRank.FIVE){
				Straight = true;
			}
			else{
				Straight = false;
			}
		}
			//Straight, no Ace
		else if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == CardsInHand
				.get(eCardNum.SecondCard.getCardNum()).getRank().getRank() + 1
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == CardsInHand
				.get(eCardNum.ThirdCard.getCardNum()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == CardsInHand
				.get(eCardNum.FourthCard.getCardNum()).getRank().getRank() + 3
				&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == CardsInHand
				.get(eCardNum.FifthCard.getCardNum()).getRank().getRank() + 4){
			Straight = true;
		}
		else {
			Straight = false;
		}
		// Royal flush
		if (Straight == true
				&& Flush == true 
				&& CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank() ==
				eRank.TEN && Ace) {
			ScoreHand(eStrength.RoyalFlush, 0, 0, 0);
		}
		//Straight flush
		else if (Straight == true && Flush == true){
			ScoreHand(eStrength.StraightFlush,
					CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank(),
					0,0);
		}
		
		//Full house
		if(CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			   CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank().getRank()
			&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			   CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank().getRank()
			&& CardsInHand.get(eCardNum.FourthCard.getCardNum()).getRank().getRank() ==
			   CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank().getRank()){
			ScoreHand(eStrength.FullHouse);
		}
		//Three Of A Kind
		else if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			   CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank().getRank()
			&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			   CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank().getRank()){
			ScoreHand(eStrength.ThreeOfAKind);
		}
		//Two Pair
		else if(CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			   CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank().getRank()
			|| CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			   CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank().getRank()
			&& CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			   CardsInHand.get(eCardNum.FourthCard.getCardNum()).getRank().getRank()
			|| CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			   CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank().getRank()){
			ScoreHand(eStrength.TwoPair);
		}
		//Pair
		else if (CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			  CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank().getRank()
			||CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			  CardsInHand.get(eCardNum.ThirdCard.getCardNum()).getRank().getRank()
			||CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() == 
			  CardsInHand.get(eCardNum.FourthCard.getCardNum()).getRank().getRank()
			||CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank().getRank() ==
			  CardsInHand.get(eCardNum.FifthCard.getCardNum()).getRank().getRank()){
			ScoreHand(eStrength.Pair);
		}
		
		//High Card
		else{
			ScoreHand(eStrength.HighCard,
					CardsInHand.get(eCardNum.FirstCard.getCardNum()).getRank()
					.getRank(),0,
					CardsInHand.get(eCardNum.SecondCard.getCardNum()).getRank()
					.getRank());
		}
		
		private static void ScoreHand(eStrength hST, int HighHand, int LowHand, int Kicker){
			this.HandStrength = hST.getStrength();
			this.HighHand = HighHand;
			this.LowHand = LowHand;
			this.Kicker = Kicker;
			this.bScored = true;
		}
		
		public static Comparator<Hand> HandRank = new Comparator<Hand>(){
			public int compare(Hand hand1, Hand hand2){
				int result = 0;
				result = hand2.getHandStrength()- hand1.getHandStrength();
				if (result != 0){
					return result;
				}
				result = hand2.getHighPairStrength()-hand1.getHighPairStrength();
				if (result != 0){
					return result;
				}
				result = hand2.getLowPairStrenght()-hand1.getLowPairStrenght();
				if (result !=0){
					return result;
				}
				result = hand2.getKicker()-hand1.getKicker();
				if (result != 0){
					return result;
				}
				return 0;
			}
		};
	}
}
