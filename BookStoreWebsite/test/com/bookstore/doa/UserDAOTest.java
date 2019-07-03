package com.bookstore.doa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClas() throws Exception {
		userDAO = new UserDAO();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("letsRock@hotmail.com");
		user1.setFullName("Rocy");
		user1.setPassword("1112");

		userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);

	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(19);
		user.setEmail("na@hotmail.com");
		user.setFullName("King");
		user.setPassword("lol");

		user = userDAO.update(user);

		String expected = "lol";
		String actual = user.getPassword();

		assertEquals(expected, actual);

	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 20;
		Users user = userDAO.get(userId);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);

	}

	@Test
	public void testGetUserNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);

		assertNull(user);

	}

	@Test
	public void testDeleteUsers() {
		Integer userId = 19;
		userDAO.delete(userId);

		Users user = userDAO.get(userId);
		assertNull(user);
	}

	@Test(expected = Exception.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 35;
		userDAO.delete(userId);

	}

	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for (Users user : listUsers) {
			System.out.println(user.getFullName());
		}
		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testCount() {
		long totalUsers = userDAO.count();

		assertTrue(totalUsers > 0);
	}

	@Test
	public void testFindByEmail() {
		String email = "dlc@hotmail.com";
		Users users = userDAO.findByEmail(email);

		assertNotNull(users);
	}

	@Test
	public void testCheckLoginSuccess() {
		String email = "dlc@hotmail.com";
		String password = "Maid";

		boolean loginResult = userDAO.checkLogin(email, password);

		assertTrue(loginResult);

	}

	@Test
	public void testCheckLoginFailed() {
		String email = "dl@hotmail.com";
		String password = "Mad";

		boolean loginResult = userDAO.checkLogin(email, password);

		assertFalse(loginResult);

	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		userDAO.close();
	}

}
