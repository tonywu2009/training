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

public class AuthorDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1619700647002508164L;

	public void addAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "insert into tbl_author (authorName) values (?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, author.getAuthorName());
			pstmt.executeUpdate();

	}
	
	public void updateAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "update tbl_author set authorName = ? where authorId = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, author.getAuthorName());
			pstmt.setInt(2, author.getAuthorId());
			pstmt.executeUpdate();

	}
	
	public void removeAuthor(Author author) throws SQLException {
		Connection conn = getConnection();

			String removeQuery = "delete from tbl_author where authorId=?";
			PreparedStatement pstmt = conn.prepareStatement(removeQuery);
			pstmt.setInt(1, author.getAuthorId());
			pstmt.executeUpdate();

	}
	
	public Author getByAuthorName(String author) throws SQLException {
		Author a = new Author();
		String select = "select * from tbl_author where authorName = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, author);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			a.setAuthorId(set.getInt("authorId"));
			a.setAuthorName(set.getString("authorName"));
		}
		return a;
		
	}
	
	public Author getByAuthorID(int authorID) throws SQLException {
		Author a = new Author();
		String select = "select * from tbl_author where authorID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, authorID);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			a.setAuthorId(set.getInt("authorId"));
			a.setAuthorName(set.getString("authorName"));
		}
		return a;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	public List<Book> getBookList(int authorId) throws SQLException {
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
	}
	
	public List<Author> readAll() throws SQLException {
		String select = "select * from tbl_author";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		
		List<Author> authors = new ArrayList<Author>();
		while(rs.next()) {
			Author a = new Author();
			int authId = rs.getInt("authorId");
			a.setAuthorId(authId);
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(getBookList(authId));
			authors.add(a);
		}
		return authors;
	}
	
	
}
