/* *****************************************************************************
 *              ALL STUDENTS COMPLETE THESE SECTIONS
 * Title:            Cards Driver
 * Files:            CardsDriver.java, Card.java, Deck.java, Blackjack.java
 * Semester:         Spring 2022
 * 
 * Author:           Celine Nakpil (celine.nakpil@colorado.edu)
 * 
 * Description:		 Creates a deck and draws a hand of cards before offering to play a game of Blackjack.
 * 
 * Written:       	 01 March 2022
 * 
 * Credits:          N/A
 **************************************************************************** */
import java.util.Scanner;

/**
 * A program to test the Deck and Cards classes. Attempts to deal a hand
 * and print it out.
 * 
 * <p>
 * Bugs: None as of 01 March 2022
 * 
 * @author Celine Nakpil
 *
 */
public class CardsDriver {

	/**
	 * Main entry point for the program. Attempts to create a deck and deal a hand.
	 * 
	 * @param args No command-line arguments expected.
	 */
	public static void main(String[] args) {
		
		// Create a new deck
		Deck deck = new Deck();
		//System.out.println(deck); // uncomment if you want to print the pre-shuffled deck out
		
		//System.out.println("------------");
		
		// Shuffle the deck 
		deck.shuffle();
		//System.out.println(deck); // uncomment if you want to print out the shuffled deck
		
		// Try to deal a 2 card hand
		Card[] hand = new Card[2];
		for(int i = 0; i < 2; i++) {
			hand[i] = deck.dealCard();
		}
		
		// Print out the hand that was dealt
		System.out.println("Your hand: ");
		for(int i = 0; i < 2; i++) {
			System.out.println(hand[i]);
		}
		
		// Print numerical total of your hand
		int total;
		total = hand[0].getValue() + hand[1].getValue();
		System.out.println("Current total: "+total);
		
		// Could extend this to play some games, e.g., high/low, war, poker...
		blackjack();
	}
	
	/**
	 * Runs the blackjack game by creating a blackjack object.
	 */
	private static void blackjack() {
		Scanner input = new Scanner(System.in);
		String choice = "";
		String choice2 = "";
		Boolean inputCheck;
		Boolean playCheck;
		
		do {
			inputCheck = false;
			
			System.out.println("Want to play blackjack? (Y/N)");
			choice = input.nextLine();
			choice = choice.toUpperCase();
			
			if (choice.equals("Y")) {
				Blackjack game = new Blackjack();	
				game.play();
				
				do {
					playCheck = false;
					
					System.out.println("Want to play another hand? (Y/N)");
					choice2 = input.nextLine();
					choice2 = choice2.toUpperCase();
					
					if (choice2.equals("Y")) {
						game.clear();
						game.setup();
						game.play();
						playCheck=true;
					}else if (choice2.equals("N")) {
						System.out.println();
						System.out.println("Okidoki! Have a nice day.");
					}else {
						System.out.println("Not a valid input. Please answer Y or N");
						playCheck = true;
					}
				}while(playCheck);
			}else if (choice.equals("N")) {
				System.out.println("Okidoki! Have a nice day.");
			}else {
				System.out.println("Not a valid input. Please answer Y or N");
				inputCheck = true;
			}	
		}while(inputCheck);
		
		input.close();
	}
}
