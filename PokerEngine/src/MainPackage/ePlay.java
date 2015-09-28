package MainPackage;

public enum ePlay {
	FiveStud(1),
	FiveStudOneJoker(2),
	FiveStudTwoJoker(3),
	TexasHoldEm(4),
	Omaha(5),
	DeucesWild(6),
	AcesAndEights(7),
	SevenDraw(8);
	
	private int playNbr;
	private ePlay(final int playNbr){
		this.playNbr = playNbr;
	}
	
	public int getPlay(){
		return playNbr;
	}
}
