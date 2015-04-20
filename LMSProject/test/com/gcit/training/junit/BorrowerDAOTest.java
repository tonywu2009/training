package com.gcit.training.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.gcit.lmsproject.dao.BorrowerDAO;
import com.gcit.lmsproject.domain.Borrower;

public class BorrowerDAOTest {

	//@Test
/*	public void testAddBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBorrower() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByBorrowerName() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetByCardNo() {
		BorrowerDAO dao = new BorrowerDAO();
		try {
		Borrower b = dao.getByCardNo(1);
		System.out.println(b.getCardNo());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//@Test
/*	public void testReadAll() {
		fail("Not yet implemented");
	}*/

}
