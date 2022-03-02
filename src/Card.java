/* *****************************************************************************
 *              ALL STUDENTS COMPLETE THESE SECTIONS
 * Title:            Card Constructor
 * Files:            Card.java
 * Semester:         Spring 2022
 * 
 * Author:           Celine Nakpil (celine.nakpil@colorado.edu)
 * 
 * Description:		 Creates a Card object to be used in conjunction with Deck.java and CardsDriver.java
 * 
 * Written:       	 23 February 2022
 * 
 * Credits:          N/A
 **************************************************************************** */

/**
 * A Card represents a playing card with one of four suits (spades, hearts, diamond,
 * or clubs) and a value (Ace, 2-10, Jack, Queen, or King).
 * 
 * <p>
 * Bugs: No bugs as of 23 February 2022
 * 
 * @author Celine Nakpil
 *
 */
public class Card {

	// Codes for the 4 suits
	public static final int SPADES = 0;   
	public static final int HEARTS = 1;
	public static final int DIAMONDS = 2;
	public static final int CLUBS = 3;

	public static final int ACE = 1;      // Codes for the non-numeric cards.
	public static final int JACK = 11;    //   Cards 2 through 10 have their 
	public static final int QUEEN = 12;   //   numerical values for their codes.
	public static final int KING = 13;

	/**
	 * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
	 * or CLUBS.  The suit cannot be changed after the card is
	 * constructed.
	 */
	private final int suit; 

	/**
	 * The card's value.  For a normal card, this is one of the values
	 * 1 through 13, with 1 representing ACE. 
	 * The value cannot be changed after the card
	 * is constructed.
	 */
	private final int value;

	/**
	 * Creates a card with a specified suit and value.
	 * 
	 * @param value the value of the new card, which must be in 
	 * 				   the range 1 through 13, with 1 representing an Ace.
	 * 				   You can use the constants Card.ACE, Card.JACK, 
	 * 				   Card.QUEEN, and Card.KING.  
	 * 
	 * @param suit the suit of the new card. This must be one of the 
	 * 				  values Card.SPADES, Card.HEARTS, Card.DIAMONDS, 
	 * 				  or Card.CLUBS.
	 * 
	 * @throws IllegalArgumentException if the parameter values are not 
	 * 								    in the permissible ranges
	 */
	public Card(int value, int suit) {
		//TODO: Your code here
		if (suit<0 || suit>3)
	         throw new IllegalArgumentException("Suit must be between 0 and 3");
		else if (value<0 || value>13)
			throw new IllegalArgumentException("Value must be between 0 and 13");
		
	    this.suit = suit;
	    this.value = value;
	}

	/**
	 * Returns the suit of this card.
	 * 
	 * @returns the suit, which is one of the constants Card.SPADES, 
	 * Card.HEARTS, Card.DIAMONDS, or Card.CLUBS
	 */
	public int getSuit() {
		//TODO: Your code here
		return suit;
	}

	/**
	 * Returns the value of this card.
	 * 
	 * @return the value, which is one of the numbers 1 through 13
	 */
	public int getValue() {
		//TODO: Your code here
		return value;
	}

	/**
	 * Returns a String representation of the card's suit.
	 * @return One of the strings "Spades", "Hearts", "Diamonds", or "Clubs"
	 */
	public String getSuitAsString() {
		String stringSuit = "";
		
		switch(suit) {
		case 0: 
			stringSuit = "Spades";
			break;
		case 1:
			stringSuit = "Hearts";
			break;
		case 2: 
			stringSuit = "Diamonds";
			break;
		case 3:
			stringSuit = "Clubs";
			break;
		}
		
		return stringSuit;
	}

	/**
	 * Returns a String representation of the card's value.
	 * 
	 * @return  One of the Strings "Ace", "2",
	 *         "3", ..., "10", "Jack", "Queen", or "King". 
	 */
	public String getValueAsString() {
		String stringValue = "";
		
		switch(value) {
		case 1:
			stringValue = "Ace";
			break;
		case 11:
			stringValue = "Jack";
			break;
		case 12:
			stringValue = "Queen";
			break;
		case 13:
			stringValue = "King";
			break;
		default:
			stringValue = Integer.toString(value);
		}
		
		return stringValue;
	}

	/**
	 * Returns a String representation of this card, including both
	 * its suit and its value. Sample return values
	 * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades"
	 * 
	 * @return A String representation of the card with its suit and value
	 */
	public String toString() {
		return getValueAsString() + " of " + getSuitAsString();
	}
}
