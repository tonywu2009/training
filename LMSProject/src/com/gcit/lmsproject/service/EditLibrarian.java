package com.gcit.lmsproject.service;

import java.util.Scanner;

public class EditLibrarian {
	
	Scanner s;
	
	public EditLibrarian(Scanner s) {
		this.s = s;
	}
	
	public void mainMenu() {
		System.out.println("1) Enter branch you manage");
		System.out.println("2) Quit to previous");
		String response = s.next();
		
	}
}
