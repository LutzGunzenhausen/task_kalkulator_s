package de.lutz.task.countryconfig;

import org.junit.Test;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.money.Money;

import static org.junit.Assert.*;

public class CountryConfigurationTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnNegativeTaxRate() {
		new CountryConfiguration("DE", "EUR", -0.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnTooBigTaxRate() {
		new CountryConfiguration("DE", "EUR", 1.1, new Money(0, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailingCreationOnFixedCostBelowZero() {
		new CountryConfiguration("DE", "EUR", 0.5, new Money(-1000, 0));
	}

	@Test(expected = NullPointerException.class)
	public void testFailingCreationOnMissingCountryCode() {
		new CountryConfiguration(null, "EUR", 0.5, new Money(1000, 0));
	}

	@Test(expected = NullPointerException.class)
	public void testFailingCreationOnMissingCurrencyCode() {
		new CountryConfiguration("DE", null, 0.5, new Money(1000, 0));
	}
	
	@Test
	public void testSuccessfulCreationOnPositiveTaxRate() {
		CountryConfiguration config = new CountryConfiguration("DE", "EUR", 0.5, new Money(100, 0));
		assertEquals("DE", config.getCountryCode());
		assertEquals("EUR", config.getCurrencyCode());
		assertEquals(0.5, config.getTaxRate(), 0.0);
		assertTrue(config.getFixedCosts().isSameAmountAs(new Money(100, 0)));
	}
}
