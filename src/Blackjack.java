/* *****************************************************************************
 * Title:            Blackjack
 * Files:            Blackjack.java
 * Semester:         Spring 2022
 * 
 * Author:           Celine Nakpil (celine.nakpil@colorado.edu)
 * 
 * Description:		 A Blackjack object represents an instance of a blackjack game.
 * 
 * Written:       	 01 March 2022
 * 
 * Credits:          N/A
 **************************************************************************** */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Object to contain, initialize, and run all parts of a Blackjack card game. 
 * Intended only for one player vs the dealer.
 * 
 * <p>
 * Bugs: Unsure if the recursion in setup() actually works. 
 * Also the bust/blackjack conditionals sometimes do not fire correctly, haven't playtested enough.
 * The play() method also technically has a memory leak. Can't figure out how to close the "in" scanner
 * without breaking the method.
 * 
 * @author Celine Nakpil
 *
 */
public class Blackjack {	
	// make new deck
	private static Deck deck = new Deck();
	
	// make dealer hand
	private static List<Card> dealer = new ArrayList<Card>();
	
	// make player hand
	private static List<Card> player = new ArrayList<Card>();
	
	/**
	 * Blackjack game constructor. Clears the lists of cards if need be and runs the setup method.
	 */
	public Blackjack() {		
		clear();
		setup();
	}
	
	/**
	 * Deals a card (1) to the hand from the deck.
	 * 
	 * @param hand dealt list of card objects
	 * @param deck deck of card objects
	 * @return Returns altered card list
	 */
	private static List<Card> dealCards(List<Card> hand, Deck deck){
		hand.add(deck.dealCard());
		return hand;
	}
	
	/**
	 * Prints out the cards of a given list.
	 * 
	 * @param hand imported card list
	 * @return Returns a string of the given card list
	 */
	public static String printCards(List<Card> hand) {
		String s = "";
		
		for(int l = 0; l < hand.size(); l++) {
			if (l == hand.size()-1)
				s += hand.get(l);
			else s += hand.get(l) + ", ";
		}
		
		return s;
	}
	
	/**
	 * Loops through a list of cards and sums the integer value of the card hand
	 * 
	 * @param cards A list of cards
	 * @return Returns integer value for the sum of the given list of cards
	 */
	private static int getSum(List<Card> cards) {
		int sum = 0;
		for(int c=0; c<cards.size(); c++) {
			sum += (cards.get(c)).getValue();
		}
		return sum;
	}
	
	/**
	 * Creates a new deck and shuffles it.
	 * Deal initial cards from deck to dealer and player. 
	 * Recursively checks and re-deals cards if either starting hand is bust or blackjack. 
	 * Maybe.
	 */
	public void setup() {
		deck = new Deck();
		deck.shuffle();
		dealCards(dealer, deck);
		dealCards(dealer, deck);
		dealCards(player, deck);
		dealCards(player, deck);
		
		if(getSum(player)>=21 || getSum(dealer)>=21) {
			dealer.clear();
			player.clear();
			setup();
		}
	}
	
	/**
	 * Check for player sum end conditions: blackjack or bust
	 * 
	 * @param sum integer value for current player card sum
	 * @return Returns false if blackjack or bust. Returns true otherwise.
	 */	
	private static boolean sumCheck(int sum) {
		if(sum == 21) {
			System.out.println();
			System.out.println("Blackjack. You win!");
			System.out.println();
			return false;
		}else if (sum>21) {
			System.out.println();
			System.out.println("Bust. You lose.");
			System.out.println();
			return false;
		}else return true;
	}
	
	/**
	 * Checks the dealer's hand of cards for hit/stand loop
	 * 
	 * @param sum imports an integer for the sum of a hand of cards.
	 * @return Returns false if blackjack or bust. Returns true otherwise.
	 */
	private static boolean dealerCheck(int sum) {
		if(sum == 21) {
			System.out.println();
			System.out.println("Dealer blackjack. Dealer wins.");
			System.out.println();
			return false;
		}else if (sum>21) {
			System.out.println();
			System.out.println("Dealer bust. You win.");
			System.out.println();
			return false;
		}else return true;
	}
	
	/**
	 * When the player stands, dealer reveals second card and hits until stand, bust, or blackjack.
	 * 
	 * @param playerSum imports the player sum saved from time of player stand
	 */
	private static void dealerPlay(int playerSum){
		int dealerSum = getSum(dealer);
		boolean dealerPlay;
		
		do {
			dealerPlay = dealerCheck(dealerSum);
			
			if (21-dealerSum > 3) {
				System.out.println();
				System.out.println("Dealer hits.");
				dealCards(dealer, deck);
				dealerSum = getSum(dealer);
				System.out.println("Current dealer hand: " + printCards(dealer));
				System.out.println("Dealer card total: " + dealerSum);
				dealerPlay = dealerCheck(dealerSum);
			}else {
				System.out.println("Dealer stands.");
				System.out.println();
				System.out.println("Current player card total: "+ playerSum);
				System.out.println();
				if (dealerSum == playerSum) {
					System.out.println("Round ends in a tie.");
					System.out.println();
					dealerPlay = false;
				}else if(21-dealerSum < 21-playerSum) {
					System.out.println("Dealer is closer to 21. Dealer wins.");
					System.out.println();
					dealerPlay = false;
				}else {
					System.out.println("Player is closer to 21. Player wins.");
					System.out.println();
					dealerPlay = false;
				}
			}
		}while(dealerPlay);
	}
	
	/**
	 * Clears the dealer and player lists of any previous cards.
	 */
	public void clear(){
		dealer.clear();
		player.clear();
	}
	
	/**
	 * Main game loop to play blackjack.
	 */
	public void play() {		
		boolean keepPlaying;
		Scanner in = new Scanner(System.in);
		String BJchoice;
		int pSum = getSum(player);
		
		System.out.println();
		System.out.println("Dealer face-up card: " + dealer.get(1));
		System.out.println();
		System.out.println("Your cards: " + printCards(player));
		System.out.println("Your current card total: " + getSum(player));
		System.out.println();
		
		do {
			keepPlaying = false;
	
			System.out.println("Hit or stand?");
			BJchoice = in.nextLine();
			BJchoice = BJchoice.toUpperCase();
			
			if (BJchoice.equals("HIT")) {
				dealCards(player, deck);
				pSum = getSum(player);
				System.out.println();
				System.out.println("Your cards: " + printCards(player));
				System.out.println("Your current card total: " + pSum);
				keepPlaying = sumCheck(pSum);
			}else if(BJchoice.equals("STAND")) {
				pSum = getSum(player);
				System.out.println();
				System.out.println("Your current card total: " + pSum);
				System.out.println();
				System.out.println("Dealer reveals face down card.");
				System.out.println("Current dealer hand: "+printCards(dealer));
				System.out.println("Dealer card total: " + getSum(dealer));
				dealerPlay(pSum);
			}else {
				System.out.println("Not a valid input. Please answer 'hit' or 'stand'");
				keepPlaying = true;
			}
		}while(keepPlaying);
	}
}
