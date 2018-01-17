package de.lutz.task.money;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {
	
	@Test
	public void testCreateMone() {
		Money money = new Money(13, 37);
		assertMoney(money, 13, 37);
	}
	
	@Test
	public void testAddPositiveMoney() {
		Money first = new Money(100, 50);
		Money second = new Money(100, 50);
		Money result = first.add(second);
		assertMoney(result, 201, 0);
	}
	
	@Test
	public void testAddNegativeMoney() {
		Money first = new Money(100, 50);
		Money second = new Money(-50, 50);
		Money result = first.add(second);
		assertMoney(result, 50, 0);
	}
	
	@Test
	public void testSubtractPositiveMoney() {
		Money first = new Money(100, 50);
		Money second = new Money(50, 75);
		Money result = first.subtract(second);
		assertMoney(result, 49, 75);
	}

	@Test
	public void testSubtractNegativeMoney() {
		Money first = new Money(100, 50);
		Money second = new Money(-50, 75);
		Money result = first.subtract(second);
		assertMoney(result, 151, 25);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailedCreationOnNegativeCents() {
		new Money(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailedCreationOnTooBigCentsAmount() {
		new Money(0, 100);
	}
	
	@Test
	public void testMultiplication() {
		Money money = new Money(100, 0);
		Money result = money.multiply(1.0 / 3);
		assertMoney(result, 33, 33);
	}
	
	@Test
	public void testCompareSmaller() {
		Money first = new Money(1, 0);
		Money second = new Money(2, 0);
		assertTrue(first.isLesserThan(second));
		assertFalse(first.isSameAmountAs(second));
		assertFalse(first.isGreaterThan(second));
	}
	
	@Test
	public void testCompareBigger() {
		Money first = new Money(2, 0);
		Money second = new Money(1, 0);
		assertTrue(first.isGreaterThan(second));
		assertFalse(first.isSameAmountAs(second));
		assertFalse(first.isLesserThan(second));
	}

	@Test
	public void testCompareSame() {
		Money first = new Money(1, 0);
		Money second = new Money(1, 0);
		assertTrue(first.isSameAmountAs(second));
		assertFalse(first.isGreaterThan(second));
		assertFalse(first.isLesserThan(second));
	}

	public void assertMoney(Money money, final int expectedDenominator, final int expectedCents) {
		assertEquals(expectedDenominator, money.getDenominator());
		assertEquals(expectedCents, money.getCents());
	}
}
