package com.gcit.training.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gcit.lmsproject.dao.BookDAO;
import com.gcit.lmsproject.domain.Author;
import com.gcit.lmsproject.domain.Book;

public class BookDAOTest {

/*	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByBookTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByBookID() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testReadAll() {
		List<Book> bookList = new ArrayList<Book>();
		try {
			bookList = new BookDAO().readAll();
					
			for(Book b2 : bookList) {
				List<Author> a = b2.getAuthors();
				for(Author a2 : a) {
					System.out.println(a2.getAuthorName());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
