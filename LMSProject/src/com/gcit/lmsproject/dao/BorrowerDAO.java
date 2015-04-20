package com.gcit.lmsproject.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lmsproject.domain.Author;
import com.gcit.lmsproject.domain.Book;
import com.gcit.lmsproject.domain.Borrower;

public class BorrowerDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 64833058356477644L;

	
	/*cardNo,borrowerName,borrowerAddress,borrowerPhone;*/
	
	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = getConnection();
			String insert = "insert into tbl_borrower (name, address, phone) values (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, borrower.getBorrowerName());
			pstmt.setString(2, borrower.getBorrowerAddress());
			pstmt.setString(3, borrower.getBorrowerPhone());
			pstmt.executeUpdate();

	}
	
	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "update tbl_borrower set (name, address, phone) = (?, ?, ?) where cardNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, borrower.getBorrowerName());
			pstmt.setString(2, borrower.getBorrowerAddress());
			pstmt.setString(3, borrower.getBorrowerPhone());
			pstmt.setInt(4, borrower.getCardNo());
			pstmt.executeUpdate();

	}
	
	public void removeBorrower(Borrower borrower) throws SQLException {
		Connection conn = getConnection();

			String removeQuery = "delete from tbl_borrower where cardNo =?";
			PreparedStatement pstmt = conn.prepareStatement(removeQuery);
			pstmt.setInt(1, borrower.getCardNo());
			pstmt.executeUpdate();

	}
	
	public Borrower getByBorrowerName(String borrower) throws SQLException {
		Borrower b = new Borrower();
		String select = "select * from tbl_borrower where borrowerName = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, borrower);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setCardNo(set.getInt("cardNo"));
			b.setBorrowerName(set.getString("name"));
			b.setBorrowerAddress(set.getString("address"));
			b.setBorrowerPhone(set.getString("phone"));
		}
		return b;
		
	}
	
	public Borrower getByCardNo(int cardNo) throws SQLException {
		Borrower b = new Borrower();
		String select = "select * from tbl_borrower where cardNo = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, cardNo);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setCardNo(set.getInt("cardNo"));
			b.setBorrowerName(set.getString("name"));
			b.setBorrowerAddress(set.getString("address"));
			b.setBorrowerPhone(set.getString("phone"));
		}
		return b;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
/*	public List<book> getBookList(int authorId) throws SQLException {
		String select = "select * from tbl_book inner join "
				+ "tbl_book_authors on tbl_book_authors.bookId = tbl_book.bookId "
				+ "inner join tbl_author on tbl_author.authorId = tbl_book_authors.authorId where tbl_author.authorId = ?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		stmt.setInt(1, authorId);
		ResultSet rs = stmt.executeQuery();
		List<Book> blist = new ArrayList<Book>();
		while(rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId")); //book id
			b.setTitle(rs.getString("title")); //title
			b.setPublisherId(rs.getInt("pubId"));
			blist.add(b);
		}
		return blist;
	}*/
	
	public List<Borrower> readAll() throws SQLException {
		String select = "select * from tbl_borrower";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		
		List<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setBorrowerName(rs.getString("name"));
			b.setBorrowerAddress(rs.getString("address"));
			b.setBorrowerPhone(rs.getString("phone"));
			borrowers.add(b);
		}
		return borrowers;
	}
}
