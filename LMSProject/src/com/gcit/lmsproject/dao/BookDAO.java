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

public class BookDAO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8691790114939230960L;

	public void addBook(Book book) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "insert into tbl_book (title, pubId) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2,  book.getPublisherId());
			pstmt.executeUpdate();

	}
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = getConnection();
			String updateQuery = "update tbl_book set (title, pubId) = (?, ?) where bookId = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getPublisherId());
			pstmt.setInt(3, book.getBookId());
			pstmt.executeUpdate();

	}
	
	public void removeBook(Book book) throws SQLException {
		Connection conn = getConnection();

			String removeQuery = "delete from tbl_book where bookId=?";
			PreparedStatement pstmt = conn.prepareStatement(removeQuery);
			pstmt.setInt(1, book.getBookId());
			pstmt.executeUpdate();

	}
	
	public Book getByBookTitle(String book) throws SQLException {
		Book b = new Book();
		String select = "select * from tbl_book where title = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, book);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setBookId(set.getInt("bookId"));
			b.setTitle(set.getString("title"));
		}
		return b;
		
	}
	
	public Book getByBookID(int bookID) throws SQLException {
		Book b = new Book();
		String select = "select * from tbl_book where bookID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, bookID);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			b.setBookId(set.getInt("bookId"));
			b.setTitle(set.getString("title"));
		}
		return b;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	public List<Author> getAuthorList(int bookId) throws SQLException {
		String select = "select * from tbl_author inner join "
				+ "tbl_book_authors on tbl_author.authorId = tbl_book_authors.authorId "
				+ "inner join tbl_book on tbl_book.bookId = tbl_book_authors.bookId where tbl_book.bookId = ?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		stmt.setInt(1, bookId);
		ResultSet rs = stmt.executeQuery();
		List<Author> a = new ArrayList<Author>();
		while(rs.next()) {
			Author auth = new Author();
			auth.setAuthorId(rs.getInt("authorId"));
			auth.setAuthorName(rs.getString("authorName"));
			a.add(auth);
		}
		return a;
	}
	
	public List<Book> readAll() throws SQLException {
		String select = "select * from tbl_book";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		List<Book> books = new ArrayList<Book>();
		while(rs.next()) {
			Book b = new Book();
			int bookId = rs.getInt("bookId");
			b.setBookId(bookId);
			b.setTitle(rs.getString("title"));
			b.setPublisherId(rs.getInt("pubId"));
			List<Author> authors = getAuthorList(bookId);
			b.setAuthors(authors);
			books.add(b);
		}
		return books;
	}
	
	
	
	
}
