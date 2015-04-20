package com.gcit.lmsproject.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.gcit.lmsproject.domain.Book;
import com.gcit.lmsproject.domain.BookLoan;

public class BookLoanDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7618168280182893299L;
	
	//library branch
	//borrower id
	//bookid
	//bookloan
	
	public void loanBook(BookLoan loan) throws SQLException {
		Connection conn = getConnection();
		//query the database for a bookId inner join tbl_book_loans on tbl_book_loans.bookIdtbl_library_branch on tbl_library_branch.branchId = 
		
		//access the borrower -> cardNo
		//updates the book loan -> bookid, branchId, cardNo, dateOut, dueDate
		//access the branch -> BrandId
		//access the book -> bookId
		
		
		//Gets the book loan object and inserts the book loan to the 
		String loanQuery = "insert into tbl_book_loan (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?)";//bookid equals the bookloan bookid where the title = title
		PreparedStatement stmt = conn.prepareStatement(loanQuery);
		stmt.setInt(1, loan.getBook().getBookId());
		stmt.setInt(2, loan.getLibraryBranch().getBranchId());
		stmt.setInt(3, loan.getBorrower().getCardNo());
		java.sql.Date sqlDate = new java.sql.Date(java.util.Date().getTime());
		stmt.setDate(4, loan.getDateOut());
		stmt.setDate(5, loan.getDateDue());
		stmt.executeUpdate();
		
	}
	
	public void returnBook(BookLoan loan) throws SQLException {
		Connection conn = getConnection();

		String returnBook = "delete from tbl_book_loan where bookId = ? and branchId = ?";
		PreparedStatement pstmt = conn.prepareStatement(returnBook);
		pstmt.setInt(1, loan.getBook().getBookId());
		pstmt.setInt(2, loan.getLibraryBranch().getBranchId());
		pstmt.executeUpdate();
	}
	
	public void changeDueDate(BookLoan loan) throws SQLException {
		Connection conn = getConnection();

		String update = "update tbl_book_loans set dueDate = ?";
		PreparedStatement pstmt = conn.prepareStatement(update);
		pstmt.setDate(1, java.sql.Date(loan.getDateDue()));
		pstmt.executeUpdate();
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
}
