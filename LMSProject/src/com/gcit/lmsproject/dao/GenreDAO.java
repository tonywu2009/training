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
import com.gcit.lmsproject.domain.Genre;

public class GenreDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1958417200753908722L;
	
	public void addGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();
		
		//tbl_genre - genre_id, genre_name
		
		//tbl_book_genre
		//genre_id
		//bookId
		//List of books

			String updateQuery = "insert into tbl_genre (genre_id, genre_name) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setInt(1, genre.getGenreId());
			pstmt.setString(2, genre.getName());
			pstmt.executeUpdate();

	}
	
	public void updateGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "update tbl_genre set genre_name = ? where genre_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, genre.getName());
			pstmt.setInt(2, genre.getGenreId());
			pstmt.executeUpdate();

	}
	
	public void removeGenre(Genre genre) throws SQLException {
		Connection conn = getConnection();

			String removeQuery = "delete from tbl_genre where genre_id=?";
			PreparedStatement pstmt = conn.prepareStatement(removeQuery);
			pstmt.setInt(1, genre.getGenreId());
			pstmt.executeUpdate();

	}
	
	public Genre getByGenreName(String genre) throws SQLException {
		Genre g = new Genre();
		String select = "select * from tbl_genre where genre_name = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, genre);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			g.setGenreId(set.getInt("genre_id"));
			g.setName(set.getString("genre_name"));
		}
		return g;
		
	}
	
	public Genre getByGenreID(int genreID) throws SQLException {
		Genre g = new Genre();
		String select = "select * from tbl_genre where genre_id = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, genreID);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			g.setGenreId(set.getInt("genre_id"));
			g.setName(set.getString("genre_name"));
		}
		return g;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	public List<Book> getBookList(int genreId) throws SQLException {
		String select = "select * from tbl_book inner join "
				+ "tbl_book_genres on tbl_book_genres.bookId = tbl_book.bookId where tbl_genre.genre_id = ?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		stmt.setInt(1, genreId);
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
	
	public List<Genre> readAll() throws SQLException {
		String select = "select * from tbl_genre";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		
		List<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			Genre g = new Genre();
			int genreId = rs.getInt("genre_id");
			g.setGenreId(genreId);
			g.setName(rs.getString("genre_name"));
			g.setBooks(getBookList(genreId));
			genres.add(g);
		}
		return genres;
	}

}
