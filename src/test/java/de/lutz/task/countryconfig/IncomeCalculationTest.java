package de.lutz.task.countryconfig;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.lutz.task.money.Money;

public class IncomeCalculationTest {
	@Test
	public void testCalculateIncome() {
		IncomeCalculation calculation = new IncomeCalculation(new Money(100, 0), new Money(100, 0),
				0.5, "EUR", "PLN", 2.0);
		assertEquals(new Money(100, 0), calculation.getDailyGrossIncome());
		assertEquals(new Money(2200, 0), calculation.getMonthlyGrossIncome());
		assertEquals(new Money(100, 0), calculation.getCountrySpecificFixedCosts());
		assertEquals(new Money(2100, 0), calculation.getTaxableIncome());
		assertEquals(new Money(1050, 0), calculation.getTaxAmount());
		assertEquals(new Money(1050, 0), calculation.getMonthlyNetIncomeInOriginCurrency());
		assertEquals(new Money(525, 0), calculation.getMonthlyNetIncomeInDestinationCurrency());
		assertEquals(0.5, calculation.getTaxRate(), 0.0);
		assertEquals("EUR", calculation.getOriginCurrency());
		assertEquals("PLN", calculation.getDestinationCurrency());
		assertEquals(2.0, calculation.getExchangeRate(), 0.0);
	}

	@Test
	public void testCalculateIncomeWithTaxableIncomeBelowZero() {
		IncomeCalculation calculation = new IncomeCalculation(new Money(10, 0), new Money(1000, 0),
				0.5, "EUR", "PLN", 2.0);
		assertEquals(new Money(10, 0), calculation.getDailyGrossIncome());
		assertEquals(new Money(220, 0), calculation.getMonthlyGrossIncome());
		assertEquals(new Money(1000, 0), calculation.getCountrySpecificFixedCosts());
		assertEquals(new Money(-780, 0), calculation.getTaxableIncome());
		assertEquals(new Money(0, 0), calculation.getTaxAmount());
		assertEquals(new Money(-780, 0), calculation.getMonthlyNetIncomeInOriginCurrency());
		assertEquals(new Money(-390, 0), calculation.getMonthlyNetIncomeInDestinationCurrency());
		assertEquals(0.5, calculation.getTaxRate(), 0.0);
		assertEquals("EUR", calculation.getOriginCurrency());
		assertEquals("PLN", calculation.getDestinationCurrency());
		assertEquals(2.0, calculation.getExchangeRate(), 0.0);
	}
}
