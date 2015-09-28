package MainPackage;

public enum eStrength {
	FiveOfAKind(110){
		public String toString(){
			return "Five of a kind";
		}
	},
	RoyalFlush(100){
		public String toString(){
			return "Royal flush";
		}
	},
	StraightFlush(90){
		public String toString(){
			return "Straight flush";
		}
	},
	FourOfAKind(80){
		public String toString(){
			return "Four of a kind";
		}
	},
	FullHouse(70){
		public String toString(){
			return "Full house";
		}
	},
	Flush(60){
		public String toString(){
			return "Flush";
		}
	},
	Straight(50){
		public String toString(){
			return "Straight";
		}
	},
	ThreeOfAKind(40){
		public String toString(){
			return "Three of a kind";
		}
	},
	TwoPair(30){
		public String toString(){
			return "Two pairs";
		}
	},
	Pair(20){
		public String toString(){
			return "One pair";
		}
	},
	HighCard(10){
		public String toString(){
			return "High card";
		}
	};
	private eStrength(final int strength){
		this.iStrength = strength;
	}
	private int iStrength;
	public int getStrength(){
		return iStrength;
	}
}
