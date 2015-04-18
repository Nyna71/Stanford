package assignment3;
/**
 * This class implements a card from a traditional card game.
 * @param inRank The rank of the card, from Ace to King.
 * @param inSuit The suit (Clubs, Diamonds, Hearts, Spades) of the card.
 * @author Jonathan Puvilland
 *
 */
public class Card {
	
	public static final int CLUBS = 0;
	public static final int DIAMONDS = 1;
	public static final int HEARTS = 2;
	public static final int SPADES = 3;
	
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	
	public Card (int cardRank, int cardSuit) {
		rank = cardRank;
		suit = cardSuit;
	};

	/**
	 * Returns the rank of the card, as an integer between 1 and 13.
	 * @return Rank of the card.
	 */
	public int getRank() {
		return(rank);
	};
	
	/**
	 * Returns the suit of the card, as an integer between 1 and 4.
	 * @return Suit of the card.
	 */
	public int getSuit() {
		return(suit);
	};
	
	/**
	 * Returns the textual description of the card, expressed as <i>rank</i> of <i>suit</i>.
	 */
	public String toString() {
		String string = "";
		switch (rank) {
		case ACE: string += "Ace of "; break;
		case JACK: string += "Jack of "; break;
		case QUEEN: string += "Queen of "; break;
		case KING: string += "King of "; break;
		default: string += rank + " of "; 
		}
		
		switch (suit) {
		case CLUBS: string += "Clubs"; break;
		case DIAMONDS: string += "Diamonds"; break;
		case HEARTS: string += "Hearts"; break;
		case SPADES: string += "Spades"; break;
		}
		return(string);
	};
	
	private int rank;
	private int suit;
}
