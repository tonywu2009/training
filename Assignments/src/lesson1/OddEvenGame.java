/**
 * @author Tony Wu
 * Lesson 1 - Exercise 2
 * April 13, 2015
 */

package lesson1;

import java.util.Scanner;


public class OddEvenGame {
	public Player p1;
	public Player p2;
	public int chips;
	public int maxTake;
    public Scanner s;
    public boolean turn;
    
    public OddEvenGame() {
    	s = new Scanner(System.in);
    	turn = true;
    	System.out.print("What is the name of the first player? ");
    	String firstPlayer = s.next();
		p1 = new Player(firstPlayer);
		System.out.print("What is the name of the second player? ");
		String secondPlayer = s.next();
		
		//Catch error if both players have the same name
		while (p1.getName().equalsIgnoreCase(secondPlayer)) {
			System.out.print("Both players cannot be named " + secondPlayer + " Enter a different name: ");
			secondPlayer = s.next();
		}
		p2 = new Player(secondPlayer);
		
		//Initialize the pile
		System.out.print("How many chips does the initial pile contain? ");
		int initialCount = s.nextInt();
		
	    //Check the bounds and odd requirement
		//While one of the requirements is false, print the reason and ask to choose another count
			while (initialCount < 3) {
				System.out.print("You have to start with atleast 3 chips. Choose another number: ");
				initialCount = Integer.parseInt(s.next());
				while(initialCount % 2 == 0) {
					System.out.print("You have to start with an odd number of chips. Choose another number: ");
					initialCount = Integer.parseInt(s.next());
				}
			}
		chips = initialCount; //finally, add the initial count to the number of chips
    }
	
	public void play() {
		while (chips > 0) {
			if (turn) {
				move(p1);
				turn = false;
			} else {
				move(p2);
				turn = true;
			}
		}
		System.out.println();
		System.out.println(p1.getName() + " has " + p1.getChips() + " chips.");   // print each players chips
		System.out.println(p2.getName() + " has " + p2.getChips() + " chips.");
		if (p1.getChips() > p2.getChips()) {
			System.out.println(p1.getName() + " wins!");
		} else {
			System.out.println(p2.getName() + " wins!");
		}
		System.out.println();
	}
	
	public void move(Player p) {
		System.out.println();
		System.out.println("************************");
		System.out.println();
		System.out.println(p1.getName() + " has " + p1.getChips() + " chips.");   // print each players chips
		System.out.println(p2.getName() + " has " + p2.getChips() + " chips.");
		System.out.println("It's your turn " + p.getName()+".");
		System.out.println("There are " + chips + " chips remaining.");
		System.out.print("You may take any number of chips from 1 to " + Math.max(1, chips/2) + ". How many will you take, " + p.getName() +"? ");
		int take = s.nextInt();

		//if it is legal to take that number, add the chips to p1, reduce total chips by take
		while (!isLegal(take)) {
			if (take < 1){
				System.out.println("Illegal move you must take atleast one chip.");
			}
			if (chips/2 >= 1 && take > chips/2) {
				System.out.println("Illegal move you may not take more than " + chips/2 + " chips.");
			}
			System.out.print("How many will you take, " + p.getName() + "?");
			take = s.nextInt();
		}
		p.addChips(take);
		chips = chips - take;
	}
	
	
	// Determines legal number of chips to take
	public boolean isLegal(int n) {
		boolean legal = true;
		if (n < 1) {  // if the number taken is less than 1, false
			legal = false;
		} 
		if (chips/2 >= 1 && n > chips/2) {
			legal = false;
		}
		return legal;
	}
}
