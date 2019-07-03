package com.bookstore.doa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest {
	private static CategoryDAO categoryDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO();
	}



	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Pyton#");
		Category category = categoryDao.create(newCat);

		assertTrue(category != null && category.getCategoryId() > 0);

	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Mack fly");
		cat.setCategoryId(15);

		Category category = categoryDao.update(cat);

		assertEquals(cat.getName(), category.getName());

	}

	@Test
	public void testGetCategory() {
		Integer catId = 1;
		Category cat = categoryDao.get(catId);

		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId = 15;
		categoryDao.delete(catId);

		Category cat = categoryDao.get(catId);
		assertNull(cat);
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		listCategory.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long totalCategory = categoryDao.count();

		assertTrue(totalCategory > 0);
		System.out.println(categoryDao.count());
	}

	@Test
	public void testFindByName() {
		String name = "jqery";
		Category category = categoryDao.findByName(name);

		assertNotNull(category);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

}
