/**
 * @author Tony Wu
 * Lesson 1 - Exercise 2
 * April 13, 2015
 */

package lesson1;

public class Player {
	private String name;
	private int chips;
	
	public Player (String n) {
		this.name = n;
		this.chips = 0;
	}
	
	/*
	 * @param n 
	 */
	public void addChips(int n) {
		chips = chips + n;
	}
	
	public int getChips() {
		return chips;
	}
	
	public String getName() {
		return name;
	}

}
