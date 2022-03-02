/* *****************************************************************************
 *              ALL STUDENTS COMPLETE THESE SECTIONS
 * Title:            Deck
 * Files:            Deck.java
 * Semester:         Spring 2020
 * 
 * Author:           Daniel Szafir; daniel.szafir@colorado.edu
 * 
 * Description:		 A Deck represents a set of 52 cards.
 * 
 * Written:       	 2/20/2021
 * 
 * Credits:          Based on http://math.hws.edu/javanotes/c5/s4.html
 **************************************************************************** */


/**
 *  An object of type Deck represents a deck of playing cards. The deck
 *  is a regular poker deck that contains 52 cards.
 *  
 *  @author Daniel Szafir
 */
public class Deck {

	/**
	 * An array of 52 cards representing a deck.
	 */
	private Card[] deck;

	/**
	 * Keeps track of the number of cards that have been dealt from
	 * the deck so far.
	 */
	private int cardsUsed;

	/**
	 * Constructs a poker deck of playing cards, The deck contains
	 * the usual 52 cards. Initially the cards
	 * are in a sorted order. The shuffle() method can be called to
	 * randomize the order.
	 */
	public Deck() {
		deck = new Card[52];
		int cardCt = 0; // How many cards have been created so far.
		for (int suit = 0; suit <= 3; suit++) {
			for (int value = 1; value <= 13; value++) {
				deck[cardCt] = new Card(value,suit);
				cardCt++;
			}
		}
		cardsUsed = 0;
	}

	/**
	 * Put all the used cards back into the deck (if any), and
	 * shuffle the deck into a random order.
	 */
	public void shuffle() {
		for ( int i = deck.length-1; i > 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}

	/**
	 * As cards are dealt from the deck, the number of cards left
	 * decreases.  This function returns the number of cards that
	 * are still left in the deck.  The return value would be
	 * 52 when the deck is first created or after the deck has been
	 * shuffled.  It decreases by 1 each time the dealCard() method
	 * is called.
	 * 
	 * @return The number of cards left in the deck that haven't been dealt.
	 */
	public int cardsLeft() {
		return deck.length - cardsUsed;
	}

	/**
	 * Removes the next card from the deck and return it.  It is illegal
	 * to call this method if there are no more cards in the deck.  You can
	 * check the number of cards remaining by calling the cardsLeft() function.
	 * 
	 * @return the card which is removed from the deck.
	 * @throws IllegalStateException if there are no cards left in the deck
	 */
	public Card dealCard() {
		if (cardsUsed == deck.length)
			throw new IllegalStateException("No cards are left in the deck.");
		cardsUsed++;
		return deck[cardsUsed - 1];
		// Programming note:  Cards are not literally removed from the array
		// that represents the deck.  We just keep track of how many cards
		// have been used.
	}

	/**
	 * Returns a String representation of the deck by concatenating the value of all the Cards
	 * (assumes the Cards also have a toString() method implemented).
	 * 
	 * @return A String that lists all the cards in the deck
	 */
	public String toString() {
		String s = "";
		for(int i = 0; i < deck.length; ++i) {
			s += deck[i] + "\n";
		}
		return s;
	} 

}
