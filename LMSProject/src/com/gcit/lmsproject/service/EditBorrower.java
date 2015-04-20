package com.gcit.lmsproject.service;

import java.sql.SQLException;
import java.util.Scanner;

import com.gcit.lmsproject.dao.BorrowerDAO;
import com.gcit.lmsproject.domain.Borrower;

public class EditBorrower {

	Scanner s;
	
	public EditBorrower(Scanner s) {
		this.s = s;
	}
	
	public void mainMenu() {
		System.out.println("Enter your card number:");
		int cardNo = Integer.parseInt(s.next());
		try {
			BorrowerDAO dao = new BorrowerDAO();
			Borrower b = dao.getByCardNo(cardNo);
			if (b.getCardNo() == cardNo) {
				borr1();
			} else {
				System.out.println("Invalid Card Number. Please try again.");
			}
		} catch (SQLException e) {
			System.out.println("Error");
		}
	}
	
	public void borr1() {
		System.out.println("1) Check out a book");
		System.out.println("2) Return a book");
		System.out.println("3) Quit to previous");
		
		
	}
}
