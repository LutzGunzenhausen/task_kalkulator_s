package de.lutz.task.income;

import org.junit.Test;

import de.lutz.task.money.Money;

import static org.junit.Assert.*;

public class IncomeCalculatorTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnNegativeTaxRate() {
		new IncomeCalculator(-0.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnTooBigTaxRate() {
		new IncomeCalculator(1.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnTaxExemptionAmountBelowZero() {
		new IncomeCalculator(0.5, new Money(-1000, 0));
	}
	
	@Test
	public void testSuccessfulCreationOnPositiveTaxRate() {
		IncomeCalculator income = new IncomeCalculator(0.5, new Money(100, 0));
		assertEquals(0.5, income.getTaxRate(), 0.0);
		assertEquals(new Money(100, 0), income.getTaxExemptionAmount());
	}
	
	@Test
	public void testCalculateIncomeDoesNotExceedTaxExemptionRate() {
		IncomeCalculator income = new IncomeCalculator(0.5, new Money(100, 0));
		Money result = income.calculateIncome(new Money(100, 0));
		assertEquals(new Money(100, 0), result);
	}
	
	@Test
	public void testCalculateIncomeExceedsTaxExemptionRate() {
		IncomeCalculator income = new IncomeCalculator(0.5, new Money(100, 0));
		Money result = income.calculateIncome(new Money(110, 0));
		assertEquals(new Money(105, 0), result);
	}
}
