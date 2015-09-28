package MainPackage;

public enum eCardNum {

	FirstCard (0), SecondCard (1), ThirdCard(2), FourthCard(3), FifthCard(4);
	
	
	//declaration and constructor for card number
	private int cardNum;

	private eCardNum(final int cardNum){
		this.cardNum = cardNum;
		
	}
	public int getCardNum(){
		return cardNum;
	}
}
