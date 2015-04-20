package LibraryManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EditBookAuthor {
	String query;
	Scanner s;
	Connection conn;
	Administrator a;
	
	
	
	public EditBookAuthor(Scanner s, Administrator a) {
		this.s = s;
		this.query = query;
		DatabaseConnection c = new DatabaseConnection();
		conn = c.getConnection();
		this.a = a;
	}
	
	public void editBookMenu() {
		//prompts user to edit book and author
		System.out.println("1) add book");
		System.out.println("2) add author");
		System.out.println("3) update book");
		System.out.println("4) update author");
		System.out.println("5) delete book");
		System.out.println("6) delete author");
		System.out.println("7) return to previous menu");
		String input = s.next();
		String data = "";
		switch(input) {
		case "1":
			System.out.println("What is the name of the book you would like to add?");
			String title = s.nextLine();
			title = s.next();
			addBook(title);
			break;
		case "2":
			System.out.println("What is the name of the author you would like to add?");
			data = s.nextLine();
			addAuthor(data);
			break;
		case "3":
			System.out.println("What is the name of the book you would like to update?");
			data = s.nextLine();
			updateBook(data);
			break;
		case "4":
			System.out.println("What is the name of the author you would like to update?");
			data = s.nextLine();
			updateAuthor(data);
			break;
		case "5":
			System.out.println("What is the name of the book you would like to update?");
			data = s.nextLine();
			deleteBook(data);
			break;
		case "6":
			System.out.println("What is the name of the author you would like to delete?");
			data = s.nextLine();
			deleteAuthor(data);
			break;
		case "7":
			// closes connection and returns to main administrator menu;
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.adminMenu();
		default:
			System.out.println("Please enter a valid selection");
		}
	}	
	
	public void addBook(String title) {
		String addBookQuery = "insert into tbl_book (title) values (?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(addBookQuery);
			pstmt.setString(1, title);
			pstmt.executeUpdate();
			
/*			System.out.println("You have added a book " + title + " successfully");
			String query = "select * from tbl_book";
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(query);
			while(set.next()) {
				System.out.println("Book id: " + set.getInt("bookID"));
				System.out.println("Author name: " + set.getString("title"));
			}*/
			pstmt.close();
		} catch(SQLException e){
			e.printStackTrace();
		}

	}
	
	public void addAuthor(String name) {
		String addAuthorQuery = "insert into tbl_author (authorName) values (?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(addAuthorQuery);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateBook(String title) {
		String updateBookQuery = "update tbl_book set title = ? where bookID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateBookQuery);
			pstmt.setString(1, title);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateAuthor(String name) {
		String updateAuthorQuery = "update tbl_author set authorName = ?  where authorID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateAuthorQuery);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteBook(String title) {
		String deleteBookQuery = "delete from tbl_book where bookID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(deleteBookQuery);
			pstmt.setString(1, title);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteAuthor(String name) {
		String deleteAuthorQuery = "delete from tbl_author where authorID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(deleteAuthorQuery);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//general query
	/**
	 * @param type the type of query, 
	 *
	 * @param query
	 */
	public void bookAuthorQuery(String type, String query) {
	}
}
