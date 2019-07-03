package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		final int a = 123;
		final int b = 44223;
		final int result = calculator.add(a, b);

		final int expected = 44346;

		assertEquals(expected, result);
	}

}
