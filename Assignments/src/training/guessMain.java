/**
 * @author Tony Wu
 * Lesson 1 - Exercise 1
 * April 13, 2015
 */

package training;

import java.util.Scanner;

public class guessMain {

	public static void main(String[] args) {
		int number = 29;

		Scanner n = new Scanner(System.in);
		
		//TODO Asks to print a number
		System.out.println("Please guess a number");
		int userGuess = Integer.parseInt(n.next());
		int numGuesses = 0;
		try {
			while (userGuess < number-10 || userGuess > number+10) {
				System.out.println("Not within 10 numbers. Keep trying.");
				userGuess = Integer.parseInt(n.next());
				numGuesses++;
				if(numGuesses == 5) {
					System.out.println("Sorry");
					System.exit(1);
				}
			}
		} finally {
			System.out.println("You guessed within 10 numbers. The answer is " + number);
		}
	}
}
