package de.lutz.task.money;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyLayoutTest {
	
	private static MoneyLayout moneyFormat;
	
	@BeforeClass
	public static void initialize() {
		moneyFormat = new MoneyLayout();
	}
	
	@Test
	public void testSimpleFormat() {
		String result = moneyFormat.format(new Money(100, 12));
		assertEquals("100.12", result);
	}
	
	@Test
	public void testFormatWithoutCents() {
		String result = moneyFormat.format(new Money(100, 00));
		assertEquals("100.00", result);
	}

	@Test
	public void testNegativeValueWithoutCents() {
		String result = moneyFormat.format(new Money(-100, 00));
		assertEquals("-100.00", result);
	}

	@Test
	public void testNegativeValueWithCents() {
		String result = moneyFormat.format(new Money(-100, 25));
		assertEquals("-100.25", result);
	}
}
