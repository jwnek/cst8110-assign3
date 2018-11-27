/* Name: Jonathan Wenek
 * Lab Section: 8110-314
 * Lab Instructor: Jason Mombourquette
 * Assignment #: 3
 * Date: November 23, 2018
 * Purpose: This is the helper class Pile. It has an initial constructor for the Pile objects
 * which initializes the size of the pile to the integer passed to it. There is also a get method
 * which returns the current state of the pile called upon, and a remove method which removes an 
 * integer amount from the size of the pile which is called upon
 */

public class Pile {

	private int size;

	public Pile(int number) {
		size = number; // set the size of the pile called upon to the number which was passed
	}

	public int getSize() {
		return size; // return the size of the pile which was called upon
	}

	public void remove(int number) {
		size -= number; // remove the amount passed from the pile which was called upon
	}
}
