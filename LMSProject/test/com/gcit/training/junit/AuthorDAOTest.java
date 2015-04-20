package com.gcit.training.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gcit.lmsproject.dao.AuthorDAO;
import com.gcit.lmsproject.dao.BookDAO;
import com.gcit.lmsproject.domain.Author;
import com.gcit.lmsproject.domain.Book;

public class AuthorDAOTest {

/*	@Test
	public void testAddAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByAuthorName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByAuthorID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookList() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testReadAll() {
		List<Author> authorList = new ArrayList<Author>();
		try {
			authorList = new AuthorDAO().readAll();

			
			for(Author a : authorList) {
				List<Book> books = a.getBooks();
				for(Book b : books) {
					System.out.println(b.getTitle());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
