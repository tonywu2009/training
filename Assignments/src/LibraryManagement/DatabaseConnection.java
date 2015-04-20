package LibraryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* this class creates a database connnection and allows a method to return a connection*/

public class DatabaseConnection {
	Connection conn;
	
	public DatabaseConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}
}
