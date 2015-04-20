/** 
 *  LibraryMain.java
 *  @author Tony Wu - tonywu2009@gmail.com
 *  Created on Apr 16, 2015
 */
package com.gcit.main;

import java.util.Scanner;

import com.gcit.lmsproject.service.EditAdministrator;
import com.gcit.lmsproject.service.EditBorrower;
import com.gcit.lmsproject.service.EditLibrarian;

/**
 * @author Tony
 *
 */
public class LibraryMain {
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		
		String input = s.next();
		switch (input) {
		case "1":
			EditLibrarian l = new EditLibrarian(s);
			l.mainMenu();
			break;
		case "2":
			EditAdministrator a = new EditAdministrator(s);
			a.mainMenu();
			break;
		case "3":
		    EditBorrower b = new EditBorrower(s);
			b.mainMenu();
			break;
		default:
			System.out.println("Invalid entry");
		}
	}

}
