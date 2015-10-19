package MainPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import MainPackage.eCardNum;
import MainPackage.eStrength;
import MainPackage.eRank;

public class Hand {
	private UUID playerID;

	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HighHand;
	@XmlElement
	private int LowHand;
	@XmlElement
	private int Kicker;
	@XmlElement
	private ArrayList<Card> Kickers = new ArrayList<Card>();

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dJoker = new Deck();
	private static Deck dWilds = new Deck();

	public Hand() {

	}

	public void AddCardToHand(Card c) {
		if (this.CardsInHand == null) {
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}

	public Card GetCardFromHand(int location) {
		return CardsInHand.get(location);
	}

	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}

	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID) {
		this.playerID = playerID;
	}

	public UUID getPlayerID() {
		return playerID;
	}

	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HighHand;
	}

	public int getLowPairStrenght() {
		return LowHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;
		return h;
	}

	ArrayList<Hand> jokerHands = new ArrayList<Hand>();
	ArrayList<Hand> wildHands = new ArrayList<Hand>();

	public void Joker()
	{
		int numberOfJokers = 0;
		
		for ( int i = 0; i < 5; i++)
		{
			if (CardsInHand.get(i).getRank() == eRank.JOKER)
				
			{
				numberOfJokers++;
			}
		}
		Collections.sort(CardsInHand, Card.CardRank);
		if (numberOfJokers != 0)
		{
			for (short i = 0; i <= 3; i++) {
				eSuit SuitValue = eSuit.values()[i];			
				for (short j = 0; j <= 12; j++) {
					eRank RankValue = eRank.values()[j];				
					Card NewCard = new Card(SuitValue,RankValue, (13 * i) + j+1);
					CardsInHand.set(eCardNum.FirstCard.getCardNum(), NewCard);
					Hand HandAdd = new Hand(CardsInHand);
					jokerHands.add(HandAdd);
				}
			}
					
			for (short i=0; i<jokerHands.size(); i++)
			{
				jokerHands.get(i).EvalHand();
			}
			Collections.sort(jokerHands, HandRank);
		}
		if (numberOfJokers > 1){

		Collections.sort(jokerHands, HandRank);
			for (int i = 0; i < jokerHands.size(); i ++){
				for (short x = 0; x <= 3; x++) {
					eSuit SuitValue = eSuit.values()[i];			
					for (short j = 0; j <= 12; j++) {
						eRank RankValue = eRank.values()[j];				
						Card NewCard = new Card(SuitValue,RankValue, (13 * i) + j+1);
						CardsInHand.set(eCardNum.FirstCard.getCardNum(), NewCard);
						ArrayList<Card> HandAdd = new Hand(CardsInHand);
						HandAdd = CardsInHand;
						jokerHands.add(HandAdd);
					}
				}
			}
		}
	}

	public void Wilds(){
		int numberOfWilds = 0;
		
		for ( int i = 0; i < 5; i++)
		{
			if (CardsInHand.get(i).getRank() == eRank.JOKER)
				
			{
				numberOfWilds++;
			}
		}
		Collections.sort(CardsInHand, Card.CardRank);
		if (numberOfWilds != 0)
		{
			for (short i = 0; i <= 3; i++) {
				eSuit SuitValue = eSuit.values()[i];			
				for (short j = 0; j <= 12; j++) {
					eRank RankValue = eRank.values()[j];				
					Card NewCard = new Card(SuitValue,RankValue, (13 * i) + j+1);
					CardsInHand.set(eCardNum.FirstCard.getCardNum(), NewCard);
					Hand HandAdd = new Hand(CardsInHand);
					wildHands.add(HandAdd);
				}
			}
					
			for (short i=0; i<wildHands.size(); i++)
			{
				wildHands.get(i).EvalHand();
			}
			Collections.sort(wildHands, HandRank);
		}
		if (numberOfWilds > 1){

		Collections.sort(wildHands, HandRank);
			for (int i = 0; i < wildHands.size(); i ++){
				for (short x = 0; x <= 3; x++) {
					eSuit SuitValue = eSuit.values()[i];			
					for (short j = 0; j <= 12; j++) {
						eRank RankValue = eRank.values()[j];				
						Card NewCard = new Card(SuitValue,RankValue, (13 * i) + j+1);
						CardsInHand.set(eCardNum.FirstCard.getCardNum(), NewCard);
						ArrayList<Card> HandAdd = new Hand(CardsInHand);
						HandAdd = CardsInHand;
						wildHands.add(HandAdd);
					}
				}
			}
		}
		
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

	private void ScoreHand(eStrength highcard, int rank, int i, int rank2) {
		// TODO Auto-generated method stub

	}

	private void ScoreHand(eStrength highcard) {
		// TODO Auto-generated method stub

	}

	private Hand(eStrength hST, int HighHand, int LowHand, int Kicker) {
		this.HandStrength = hST.getStrength();
		this.HighHand = HighHand;
		this.LowHand = LowHand;
		this.Kicker = Kicker;
		this.bScored = true;
	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

			public int compare(Hand h1,Hand h2){

	int result=0;

	result=h2.getHandStrength()-h1.getHandStrength();

	if(result!=0){return result;}

	result=h2.getHighPairStrength()-h1.getHighPairStrength();if(result!=0){return result;}

	result=h2.getLowPairStrength()-h1.getLowPairStrength();if(result!=0){return result;}

	if(h2.getKicker().get(eCardNo.FirstCard.getCardNo())!=null){if(h1.getKicker().get(eCardNo.FirstCard.getCardNo())!=null){result=h2.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank()-h1.getKicker().get(eCardNo.FirstCard.getCardNo()).getRank().getRank();}if(result!=0){return result;}}

	if(h2.getKicker().get(eCardNo.SecondCard.getCardNo())!=null){if(h1.getKicker().get(eCardNo.SecondCard.getCardNo())!=null){result=h2.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank()-h1.getKicker().get(eCardNo.SecondCard.getCardNo()).getRank().getRank();}if(result!=0){return result;}}if(h2.getKicker().get(eCardNo.ThirdCard.getCardNo())!=null){if(h1.getKicker().get(eCardNo.ThirdCard.getCardNo())!=null){result=h2.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()-h1.getKicker().get(eCardNo.ThirdCard.getCardNo()).getRank().getRank();}if(result!=0){return result;}}

	if(h2.getKicker().get(eCardNo.FourthCard.getCardNo())!=null){if(h1.getKicker().get(eCardNo.FourthCard.getCardNo())!=null){result=h2.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank()-h1.getKicker().get(eCardNo.FourthCard.getCardNo()).getRank().getRank();}if(result!=0){return result;}}return 0;}};

}
