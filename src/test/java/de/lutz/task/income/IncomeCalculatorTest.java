package de.lutz.task.income;

import org.junit.Test;

import de.lutz.task.money.Money;

import static org.junit.Assert.*;

public class IncomeCalculatorTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnNegativeTaxRate() {
		new IncomeCalculator("DE", -0.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnTooBigTaxRate() {
		new IncomeCalculator("DE", 1.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnFixedCostBelowZero() {
		new IncomeCalculator("DE", 0.5, new Money(-1000, 0));
	}

	@Test(expected = NullPointerException.class)
	public void testFailingCreationOnMissingCountryCode() {
		new IncomeCalculator(null, 0.5, new Money(1000, 0));
	}
	
	@Test
	public void testSuccessfulCreationOnPositiveTaxRate() {
		IncomeCalculator income = new IncomeCalculator("DE", 0.5, new Money(100, 0));
		assertEquals("DE", income.getCountryCode());
		assertEquals(0.5, income.getTaxRate(), 0.0);
		assertTrue(income.getFixedCosts().isSameAmountAs(new Money(100, 0)));
	}
	
	@Test
	public void testCalculateIncomeBelowZero() {
		IncomeCalculator income = new IncomeCalculator("DE", 0.5, new Money(100, 0));
		Money result = income.calculateIncome(new Money(100, 0));
		assertTrue(result.isSameAmountAs(new Money(-50, 0)));
	}

	@Test
	public void testCalculatePositiveIncome() {
		IncomeCalculator income = new IncomeCalculator("DE", 0.5, new Money(100, 0));
		Money result = income.calculateIncome(new Money(500, 0));
		assertTrue(result.isSameAmountAs(new Money(150, 0)));
	}
}
