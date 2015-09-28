package MainPackage;

public enum eSuit {
	HEARTS(1), DIAMONDS(2), SPADES(3), CLUBS(4), JOKER(99);
	
	private eSuit(final int suit){
		this.suit = suit;
	}
	private int suit;
	
	public int getSuit(){
		return suit;
	}

}
