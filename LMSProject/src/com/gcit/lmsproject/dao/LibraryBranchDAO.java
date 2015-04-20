package com.gcit.lmsproject.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lmsproject.domain.Book;
import com.gcit.lmsproject.domain.LibraryBranch;

public class LibraryBranchDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7161630275693513623L;

	public void addBranch(LibraryBranch branch) throws SQLException {
		Connection conn = getConnection();
			//branchId, branchName, branchAddress
			String updateQuery = "insert into tbl_library_branch (branchName, branchAddress) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());
			pstmt.executeUpdate();

	}
	
	public void updateBranch(LibraryBranch branch) throws SQLException {
		Connection conn = getConnection();
			String updateQuery = "update tbl_library_branch set (branchName, branchAddress) = (?, ?) where branchId = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, branch.getBranchName());
			pstmt.setString(2, branch.getBranchAddress());
			pstmt.setInt(3, branch.getBranchId());
			pstmt.executeUpdate();

	}
	
	public void removeBranch(LibraryBranch branch) throws SQLException {
		Connection conn = getConnection();

			String removeQuery = "delete from tbl_library_branch where branchId=?";
			PreparedStatement pstmt = conn.prepareStatement(removeQuery);
			pstmt.setInt(1, branch.getBranchId());
			pstmt.executeUpdate();

	}
	
	public LibraryBranch getBranchByName(String branchName) throws SQLException {
		LibraryBranch b = new LibraryBranch();
		String select = "select * from tbl_library_branch where branchName = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, branchName);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setBranchId(set.getInt("branchId"));
			b.setBranchName(set.getString("branchName"));
			b.setBranchAddress(set.getString("branchAddress"));
			//TODO : create list of books field in the library branch add the list of books
		}
		return b;
		
	}
	
	public LibraryBranch getByBranchID(int branchID) throws SQLException {
		LibraryBranch b = new LibraryBranch();
		String select = "select * from tbl_library_branch where branchID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, branchID);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setBranchId(set.getInt("branchId"));
			b.setBranchName(set.getString("branchName"));
			b.setBranchAddress(set.getString("branchAddress"));
			//TODO : create list of books field in the library branch add the list of books
		}
		return b;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	//returns the list of books from a branchId
	public List<Book> getBookList(int branchId) throws SQLException {
		String select = "select * from tbl_book inner join "
				+ "tbl_book_copies on tbl_book_copies.bookId = tbl_book.bookId "
				+ "inner join tbl_library_branch on tbl_library_branch.branchId = tbl_book_copies.branchId where tbl_library_branch.branchId = ?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		stmt.setInt(1, branchId);
		ResultSet rs = stmt.executeQuery();
		List<Book> books = new ArrayList<Book>();
		while(rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisherId(rs.getInt("pubId"));
			books.add(b);
		}
		return books;
	}
	
	public List<LibraryBranch> readAll() throws SQLException {
		String select = "select * from tbl_library_branch";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		while(rs.next()) {
			LibraryBranch b = new LibraryBranch();
			int branchId = rs.getInt("branchId");
			b.setBranchId(branchId);
			b.setBranchName(rs.getString("branchName"));
			b.setBranchAddress(rs.getString("branchAddress"));
			List<Book> books = getBookList(branchId);
			b.setBooks(books);
			branches.add(b);
		}
		return branches;
	}
}
