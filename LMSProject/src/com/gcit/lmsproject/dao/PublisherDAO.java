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
import com.gcit.lmsproject.domain.Publisher;

public class PublisherDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4223612818674278210L;
	

	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();
			//pub id, name, address, phone
			String updateQuery = "insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, publisher.getName());
			pstmt.setString(2, publisher.getAddress());
			pstmt.setString(3, publisher.getPhoneNumber());
			pstmt.executeUpdate();

	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();

			String updateQuery = "update tbl_publisher set (publisherName, publisherAddress, publisherPhone) = (?, ?, ?) where publisherId = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, publisher.getName());
			pstmt.setString(2, publisher.getAddress());
			pstmt.setString(3, publisher.getPhoneNumber());
			pstmt.setInt(4, publisher.getId());
			pstmt.executeUpdate();

	}
	
	public void removePublisher(Publisher publisher) throws SQLException {
		Connection conn = getConnection();

			String removePub = "delete from tbl_publisher where publisherId = ?";
			PreparedStatement pstmt = conn.prepareStatement(removePub);
			pstmt.setInt(1, publisher.getId());
			pstmt.executeUpdate();

	}
	
	public Publisher getByPublisherName(String publisher) throws SQLException {
		Publisher p = new Publisher();
		String select = "select * from tbl_publisher where publisherName = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setString(1, publisher);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			p.setId(set.getInt("publisherId"));
			p.setName(set.getString("publisherName"));
			p.setAddress(set.getString("address"));
			p.setPhoneNumber("publisherPhone");
		}
		return p;
	}
	
	public Publisher getByPublisherID(int publisherID) throws SQLException {
		Publisher p = new Publisher();
		String select = "select * from tbl_publisher where publisherID = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(select);
		pstmt.setInt(1, publisherID);
		ResultSet set = pstmt.executeQuery();
		while(set.next()) {
			p.setId(set.getInt("publisherId"));
			p.setName(set.getString("publisherName"));
			p.setAddress("address");
			p.setPhoneNumber("publisherPhone");
		}
		return p;
	}

	private Connection getConnection() throws SQLException {
		Connection conn;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library", "root", "root");
		return conn;
	}
	
	public List<Book> getBookList(int publisherId) throws SQLException {
		String select = "select * from tbl_book inner join "
				+ "tbl_publisher on tbl_book.pubId = tbl_publisher.publisherId where tbl_publisher.publisherId = ?";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		stmt.setInt(1, publisherId);
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
	
	public List<Publisher> readAll() throws SQLException {
		String select = "select * from tbl_publisher";
		PreparedStatement stmt = getConnection().prepareStatement(select);
		ResultSet rs = stmt.executeQuery();
		
		List<Publisher> publishers = new ArrayList<Publisher>();
		while(rs.next()) {
			Publisher p = new Publisher();
			int pubId = rs.getInt("publisherId");
			p.setId(pubId);
			p.setName(rs.getString("publisherName"));
			p.setAddress(rs.getString("publisherAddress"));
			p.setPhoneNumber(rs.getString("publisherPhone"));
			p.setBooks(getBookList(pubId));
			publishers.add(p);
		}
		return publishers;
	}

}
