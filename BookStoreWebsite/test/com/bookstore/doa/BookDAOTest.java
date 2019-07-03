package com.bookstore.doa;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest {

	private static BookDAO bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}
	
	@Test
	public void testCreate2Book() throws ParseException, IOException {

		Book newBook = new Book();

		Category category = new Category("King");
		category.setCategoryId(17);
		newBook.setCategory(category);

		newBook.setTitle("Java 8 in Action");
		newBook.setAuthor("Raoul-Gabriel Urma");
		newBook.setDescription("Streams API, using real-world Java code ");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Kungen\\Desktop\\2017\\dummy-data-books\\books\\Java 8 in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);

		assertTrue(createdBook.getBookId() > 0);

	}


	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(32);

		Category category = new Category("King");
		category.setCategoryId(17);
		existBook.setCategory(category);

		existBook.setTitle("Effective Java (3nd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existBook.setPrice(40f);
		existBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		existBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Kungen\\Desktop\\2017\\dummy-data-books\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);

		Book updateBook = bookDao.update(existBook);

		assertEquals(updateBook.getTitle(), "Effective Java (3nd Edition)");

	}
	

	@Test
	public void testCreateBook() throws ParseException, IOException {

		Book newBook = new Book();

		Category category = new Category("Core java");
		category.setCategoryId(13);
		newBook.setCategory(category);

		newBook.setTitle("Effective Java (2nd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		newBook.setPrice(38.87f);
		newBook.setIsbn("0321356683");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");
		newBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Kungen\\Desktop\\2017\\dummy-data-books\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);

		assertTrue(createdBook.getBookId() > 0);

	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDao.delete(bookId);
	}

	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 32;
		bookDao.delete(bookId);

		assertTrue(true);
	}

	@Test
	public void testGetBookFail() {
		Integer bookId = 100;
		Book book = bookDao.get(bookId);
		assertNull(book);

	}

	@Test
	public void testGetBookSuccess() {
		Integer bookId = 33;
		bookDao.get(bookId);


	}

	@Test
	public void testListAl() {
		List<Book> listBooks = bookDao.listAll();

		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " - " + book.getAuthor());
		}

		assertFalse(listBooks.isEmpty());
	}

	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book book = bookDao.findByTitle(title);

		assertNull(book);
	}

	@Test
	public void testFindByTitleExist() {
		String title = "Java 8 in Action";
		Book book = bookDao.findByTitle(title);

		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
		assertNotNull(book);
	}

	@Test
	public void testCount() {
		long totalbook = bookDao.count();

		assertEquals(2, totalbook);

	}

	@Test
	public void testListByCategory() {
		int categoryId = 27;

		List<Book> listBooks = bookDao.listByCategory(categoryId);

		assertTrue(listBooks.size() > 0);
	}

	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDao.listNewBooks();
		for (Book book : listNewBooks) {
			System.out.println(book.getTitle() + " - " + book.getPublishDate());
		}

		assertEquals(3, listNewBooks.size());
	}

	@Test
	public void testSearchBookInTitle() {
		String keyword = "Java";
		List<Book> result = bookDao.search(keyword);

		for (Book book : result) {
			System.out.println(book.getTitle());
		}

		assertEquals(1, result.size());
	}

	@Test
	public void testSearchBookInAuthor() {
		String keyword = "ALAIN";
		List<Book> result = bookDao.search(keyword);

		for (Book book : result) {
			System.out.println(book.getAuthor());
		}

		assertEquals(1, result.size());
	}

	@Test
	public void testSearchBookInDescription() {
		String keyword = "API";
		List<Book> result = bookDao.search(keyword);

		for (Book book : result) {
			System.out.println(book.getDescription());
		}

		assertEquals(1, result.size());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}

}
