package MainPackage;
import java.util.ArrayList;
import java.util.UUID;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


public class Play {
		//@XmlElement
		private UUID GameID;
		//@XmlElement
		private int MaxNumOfPlayers;
		//@XmlElement
		private int NumOfCards;
		//@XmlElement
		private int NumOfJokers;
		//@XmlElement
		private ArrayList<Card> WildCards = new ArrayList<Card>();
		
		public GamePlay(Rule rle)
		{
			this.GameID = UUID.randomUUID();
			
			this.NumOfCards = rle.GetNumberOfCards();
			this.MaxNumOfPlayers = rle.GetMaxNumberOfPlayers();
			this.NumOfJokers = rle.GetNumberOfJokers();
			this.WildCards = rle.GetRuleCards();
		}
		
		public UUID GetGameID()
		{
			return this.GameID;
		}
		public int GerMaxNumberOfPlayers()
		{
			return MaxNumOfPlayers;
		}
		public int GetNumberOfCard()
		{
			return NumOfCards;
		}
		public int GerNumberOfJokers()
		{
			return NumOfJokers;
		}
		public ArrayList<Card> GetWildCards()
		{
			return this.WildCards;
		}
	}

}
