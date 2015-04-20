package LibraryManagement;

import java.util.Scanner;

public class Administrator {
	Scanner s;
	
	public Administrator(Scanner s) {
		this.s = s;
	}
	
	public boolean adminMenu() {
		System.out.println("1) Add/Update/Delete Book and Author");
		System.out.println("2) Add/Update/Delete Publishers");
		System.out.println("3) Add/Update/Delete Library Branches");
		System.out.println("4) Add/Update/Delete Borrowers");
		System.out.println("5) Over-ride Due Date for a Book Loan");
		System.out.println("6) Main Menu");
		
		String input = s.next();  //either add/update/delete [book and author, publishers, library branches, borrowers]
		switch(input) {
		case "1":
			EditBookAuthor auth = new EditBookAuthor(s, this);
			auth.editBookMenu();
			break;
		case "2":
			printEditMenu("Publishers");
			//EditPublishers pub = new EditPublishers(s, this);
			break;
		case "3":
			printEditMenu("Library Branches");
			//EditBranches branch = new EditBranches(s, this);
			break;
		case "4":
			printEditMenu("Borrowers");
			//EditBorrowers borrowers = new EditBorrowers(s, this);
			break;
		case "5":
			//do something
			break;
		case "6":
			break;
		default:
			System.out.println("Not an option");
		}
		return input.equals("6");
	}

    // this method prints the options to add/update/delete a type (books/authors, borrowers, publishers, branches)
	public String printEditMenu(String type) {
		System.out.println("1) Add " + type);
		System.out.println("2) Update " + type);
		System.out.println("3) Delete " + type);
		System.out.println("4) Quit to previous");
		return type;
	}

	
}
