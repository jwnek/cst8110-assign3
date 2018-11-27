/* Name: Jonathan Wenek
 * Lab Section: 8110-314
 * Lab Instructor: Jason Mombourquette
 * Assignment #: 3
 * Date: November 23, 2018
 * Purpose: This program is designed to allow the user to play a game of Nim with 3 piles
 * against the computer player. The user is can select any pile that has pieces remaining, and 
 * remove an amount up to the total from that pile. The object of the game is to make your opponent
 * remove the last object from the last pile.
 */

public class Assign3 {

	public static void main(String[] args) {
		Nim game = new Nim(); // initialize a new Nim object called game calling the default constructor.
		while (!game.done()) { // so long as the game isn't over, keep running
			while (!game.playerMove()); // call the playerMove method so long as they keep trying to make illegal moves
			if (!game.done()) { // check if the game is over
				game.computerMove(); // if not, let the computer take a turn
			}
		}
	}
}
