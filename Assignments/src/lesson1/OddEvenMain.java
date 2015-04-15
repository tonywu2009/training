/**
 * @author Tony Wu
 * Lesson 1 - Exercise 2
 * April 13, 2015
 */

package lesson1;

import java.util.Scanner;

public class OddEvenMain {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String play = "y";
		do {
			OddEvenGame game = new OddEvenGame();
			game.play();
			System.out.print("Play another game? (y/n) ");
			System.out.println();
			play = s.next();
		} while (play.equalsIgnoreCase("y"));
		s.close();
		System.exit(1);
	}


}
