/* Name: Jonathan Wenek
 * Lab Section: 8110-314
 * Lab Instructor: Jason Mombourquette
 * Assignment #: 3
 * Date: November 23, 2018
 * Purpose: This is the Nim class, it has a default constructor for the Nim object
 * which initializes the 3 piles by calling the helper class Pile. The Nim class
 * has all the rules for how the game behaves on the player's turn, the computer's turn,
 * and has methods to check if the game is over and to display the state of the piles.
 */

import java.util.Scanner; 
import java.util.Random;

public class Nim {
	private Pile pileA;
	private Pile pileB;
	private Pile pileC;
	private Random rnd;
	private Scanner input;
	
	public Nim() { // default constructor for the Nim class
		System.out.println("Welcome to the NIM game\nWe play by the misère rules");
		input = new Scanner(System.in); // initialize the input object as a new scanner class
		rnd = new Random(); // initialize the rnd object as a new random class
		do { // we run a do while on the pile constructor so long as any of the piles are equal to each other, this increases the difficulty of the game
			pileA = new Pile(rnd.nextInt(11) + 10); // call the initial constructor for each pile passing through a random number from 10 -20
			pileB = new Pile(rnd.nextInt(11) + 10);
			pileC = new Pile(rnd.nextInt(11) + 10);
		} while (pileA.getSize() == pileB.getSize() || pileA.getSize() == pileC.getSize() || pileB.getSize() == pileC.getSize());
	}
	
	public boolean playerMove() { // all the rules for the player's turn
		printPiles(); // display the current state of the three piles
		int remove;
		System.out.print("\nSelect a pile: "); // prompt the user to choose a pile
		String pile = input.nextLine().toUpperCase(); // initialize local variable pile to the next line and force capitalization
		if (!pile.equals("A") && !pile.equals("B") && !pile.equals("C")) { // check if the user did not enter exactly A, B or C
			System.out.println("Invalid pile letter, please select a, b, or c ");	// if not, display the error message
			return false; // reset the method
		}		
		switch (pile) { // once the user successfully enters A, B or C, run through this switch block
		case "A":	if (pileA.getSize() < 1) { // check if pile A is 0 or less (just in case)
						System.out.println("Pile a is empty, pick another"); // if so, display the error message
						return false; // reset the method
					}
					System.out.print("How many would you like to remove? "); // if pileA is > 0 then, we prompt the user how much they want to remove
					if (!input.hasNextInt()) { // checks if the user does not input an int
						System.out.println("Pick a number between 1 and " + pileA.getSize()); // if so, display the error message
						input.nextLine(); // reset the scanner, preventing the last input from carrying through when the method resets
						return false; // reset the method
					} 
					remove = input.nextInt(); // once we are sure the user has input an int, initialize the local variable remove with their input			
					if (remove > pileA.getSize() || remove < 1) { // check if their input is greater than the pile size, or if it's less than 1
						System.out.println("Pick a number between 1 and " + pileA.getSize()); // if so, display the error message
						input.nextLine(); // reset the scanner
						return false; // reset the method
					} 
					pileA.remove(remove); // if we get this far, the user input is valid, so we call the remove method passing through the user's input
					if (done()) { // check if this move ends the game
						System.out.print("Sorry: you lose"); // if so the player loses
						return true; // exit the method
					} else { // otherwise keep going
					input.nextLine();// reset the scanner
					return true; // return true, exiting the method
					}					
		case "B":	if (pileB.getSize() < 1) {
						System.out.println("Pile b is empty, pick another");
						return false;
					}
					System.out.print("How many would you like to remove? ");
					if (!input.hasNextInt()) {
						System.out.println("Pick a number between 1 and " + pileB.getSize());
						input.nextLine();
						return false;
					} 
					remove = input.nextInt();
					if (remove > pileB.getSize() || remove < 1) {
						System.out.println("Pick a number between 1 and " + pileB.getSize());
						input.nextLine();
						return false;
					}
					pileB.remove(remove);
					if (done()) {
						System.out.print("Sorry: you lose");
						return true;
					} else {
					input.nextLine();
					return true;
					}						
		case "C":	if (pileC.getSize() < 1) {
						System.out.println("Pile c is empty, pick another");
						return false;
					}
					System.out.print("How many would you like to remove? ");
					if (!input.hasNextInt()) {
						System.out.println("Pick a number between 1 and " + pileC.getSize());
						input.nextLine();
						return false;
					} 
					remove = input.nextInt();
					if (remove > pileC.getSize() || remove < 1) {
						System.out.println("Pick a number between 1 and " + pileC.getSize());
						input.nextLine();
						return false;
					}
					pileC.remove(remove);
					if (done()) {
						System.out.print("Sorry: you lose");
						return true;
					} else {
					input.nextLine();
					return true;
					}
		default: 	return false;
		}
	}

	public void computerRandomMove() {
		 // if nimSum is less than 1 (0 or less), we will randomly select a pile for the computer to choose from
		int remove;
		int pile; // variable to hold the choice of the computers pile when the player is in the winning position
		boolean next = false; // this variable is used to check if the computer has made a move this turn		
		while(!next) {
			pile = rnd.nextInt(3); // generate a random number so that the computer doesn't always pick the same pile.
			switch (pile) { // run this switch block, where case 0 = A, 1 = B, 2 = C
			case 0:	if (pileA.getSize() < 1) { // checks to make sure the randomly chosen pile has objects in it still
						break;	// if not break out of the switch
					} else {
						remove = rnd.nextInt(pileA.getSize()) + 1; // if there are objects left, the computer removes a random amount from pile A, at least 1.
						System.out.println("The computer takes " + remove + " from pile A"); // display the computer move for the user
						pileA.remove(remove); // call the remove fuction on pileA passing through the randomly generated amount
						next = true; // set next to true, ending the computers turn
						break;	// break out of the switch block				
					}
			case 1:	if (pileB.getSize() < 1) {
						break;
					} else {
						remove = rnd.nextInt(pileB.getSize()) + 1;
						System.out.println("The computer takes " + remove + " from pile B");
						pileB.remove(remove);
						next = true;
						break;			
					}
			case 2:	if (pileC.getSize() < 1) {
						break;
					} else {
						remove = rnd.nextInt(pileC.getSize()) + 1;
						System.out.println("The computer takes " + remove + " from pile C");
						pileC.remove(remove);
						next = true;
						break;			
					}
			}
			if (done()) { // check if the computer ended the game, if so display the winning message.
				System.out.print("Congrats: you win");
			}
		}
	}
	
	public void computerMove() { // method for maintaining the logic for the computers moves
		printPiles(); // this prints the state of the piles after the player's move, before the computer's move
		int nimSum = pileA.getSize() ^ pileB.getSize() ^ pileC.getSize(); // this calculates the XOR sum of all the piles
		int xa = pileA.getSize() ^ nimSum; // this calculates nimSum XOR A, B and C respectively. If any of the results is less than its respective
		int xb = pileB.getSize() ^ nimSum; // main pile, the computer will select that pile, removing enough to make the main pile equal to 
		int xc = pileC.getSize() ^ nimSum; // the xa, xb, or xc amount, thus making the nimSum of all three piles equal to 0.
		int remove;
		boolean next = false;
		
		if (!next && nimSum == 0) {
			computerRandomMove();
			return;
		}
		// as long as there are at least 2 piles with more than 1 objects left, the computer will try to make a move that leaves the nimSum = 0
		if (!next && (pileA.getSize() > 1 && pileB.getSize() > 1
				  ||  pileA.getSize() > 1 && pileC.getSize() > 1 
				  ||  pileB.getSize() > 1 && pileC.getSize() > 1)) {
			if (xa < pileA.getSize()) { // if xa < pileA the computer will choose pile A
				remove = pileA.getSize() - xa; // calculates the move needed to put the game into a winning position for the computer player
				System.out.println("The computer takes " + remove + " from pile A"); // print the computer's move
				pileA.remove(remove); // call the remove function on pile A passing through the amount needed to make nimSum = 0
				next = true; // end the computers turn by calling next = true
			} else if(xb < pileB.getSize()) {
				remove = pileB.getSize() - xb;
				System.out.println("The computer takes " + remove + " from pile B"); 
				pileB.remove(remove);
				next = true;
			} else if(xc < pileC.getSize()) {
				remove = pileC.getSize() - xc;
				System.out.println("The computer takes " + remove + " from pile C"); 
				pileC.remove(remove);
				next = true;
			}
		} // once the board reaches the end game state (only one pile is > 1, and one or no piles = 1) this logic will run instead
		if (!next && (pileA.getSize() > 1 && pileB.getSize() == 1 && pileC.getSize() == 0
				  ||  pileA.getSize() > 1 && pileB.getSize() == 0 && pileC.getSize() == 1)) {
			remove = pileA.getSize(); // remove all of pile a, leaving the player with the losing move
			System.out.println("The computer takes " + remove + " from pile A"); // print the computers move to the screen
			pileA.remove(remove); // call the remove method on pile A 
			next = true; // end the computer's turn
		} else if (!next && (pileB.getSize() > 1 && pileA.getSize() == 1 && pileC.getSize() == 0
				  		 ||  pileB.getSize() > 1 && pileA.getSize() == 0 && pileC.getSize() == 1)) {
			remove = pileB.getSize();
			System.out.println("The computer takes " + remove + " from pile B");
			pileB.remove(remove);
			next = true;
		} else if (!next && (pileC.getSize() > 1 && pileA.getSize() == 1 && pileB.getSize() == 0
						 || (pileC.getSize() > 1 && pileA.getSize() == 0 && pileB.getSize() == 1))) {
			remove = pileC.getSize();
			System.out.println("The computer takes " + remove + " from pile C");
			pileC.remove(remove);
			next = true;
		} else if (!next && (pileA.getSize() > 1 && pileB.getSize() == 0 && pileC.getSize() == 0 
						 ||  pileA.getSize() > 1 && pileB.getSize() == 1 && pileC.getSize() == 1)) {
			remove = pileA.getSize() - 1;
			System.out.println("The computer takes " + remove + " from pile A");
			pileA.remove(remove);
			next = true;
		} else if (!next && (pileB.getSize() > 1 && pileA.getSize() == 0 && pileC.getSize() == 0 
				 		 ||  pileB.getSize() > 1 && pileA.getSize() == 1 && pileC.getSize() == 1)) {
			remove = pileB.getSize() - 1;
			System.out.println("The computer takes " + remove + " from pile B");
			pileB.remove(remove);
			next = true;
		} else if (!next && (pileC.getSize() > 1 && pileA.getSize() == 0 && pileB.getSize() == 0 
						 ||  pileC.getSize() > 1 && pileA.getSize() == 1 && pileB.getSize() == 1)) { 
			remove = pileC.getSize() - 1; 
			System.out.println("The computer takes " + remove + " from pile C"); 
			pileC.remove(remove); 
			next = true;
		} else if (!next && (pileA.getSize() == 1 && pileB.getSize() == 0 && pileC.getSize() == 0 // if pileA has 1, and the others are empty, this is the losing move
						 ||  pileA.getSize() == 1 && pileB.getSize() == 1 && pileC.getSize() == 1)) { // this line catches the unlikely case that all 3 piles are 1
			System.out.println("The computer takes 1 from pile A"); 
			pileA.remove(1);
			next = true; 
		} else if (!next && pileB.getSize() == 1 && pileA.getSize() == 0 && pileC.getSize() == 0) {
			System.out.println("The computer takes 1 from pile B");
			pileB.remove(1);
			next = true;
		} else if (!next && pileC.getSize() == 1 && pileB.getSize() == 0 && pileA.getSize() == 0) {
			System.out.println("The computer takes 1 from pile C");
			pileC.remove(1);
			next = true;
		}		
		if (done()) {
			System.out.print("Congrats: you win");
		}
	}

	public boolean done() {
		if (pileA.getSize() < 1 && pileB.getSize() < 1 && pileC.getSize() < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void printPiles() {
		System.out.println("\tA \tB \tC");
		System.out.println("\t" + pileA.getSize() + "\t" + pileB.getSize() + "\t" + pileC.getSize());
	}
}
