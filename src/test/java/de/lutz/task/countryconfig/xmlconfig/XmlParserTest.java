package de.lutz.task.countryconfig.xmlconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.countryconfig.xmlconfig.data.IncomeCalculatorLoaderException;
import de.lutz.task.money.Money;

public class XmlParserTest {
	
	private static CountryConfigXmlParser parser;
	
	@BeforeClass
	public static void initialize() {
		parser = new CountryConfigXmlParser();
	}
	
	@Test
	public void testEmptyImport() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("Empty.xml");
		Collection<CountryConfiguration> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertTrue(calculators.isEmpty());
	}
	
	@Test
	public void testImportOneEntry() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("OneEntry.xml");
		Collection<CountryConfiguration> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertFalse(calculators.isEmpty());
		assertEquals(1, calculators.size());
		assertCalculator(calculators.iterator().next(), "UK", 0.25, new Money(600, 0));
	}

	@Test
	public void testImportMultipleEntries() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("MultipleEntries.xml");
		Collection<CountryConfiguration> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertFalse(calculators.isEmpty());
		assertEquals(2, calculators.size());
		Iterator<CountryConfiguration> iterator = calculators.iterator();
		assertCalculator(iterator.next(), "UK", 0.25, new Money(600, 0));
		assertCalculator(iterator.next(), "PL", 0.19, new Money(1200, 50));
	}
	
	@Test(expected = IncomeCalculatorLoaderException.class)
	public void testExceptionAtMalformedXml() throws IncomeCalculatorLoaderException {
		final String malformedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><IncomeConfiguration>";
		
		InputStream stream = new ByteArrayInputStream(malformedXml.getBytes());
		parser.loadIncomeCalculators(stream);
	}
	
	private void assertCalculator(CountryConfiguration calculator, String expectedCountryCode,
			double expectedTaxRate, Money expectedFixedCosts) {
		assertEquals(expectedCountryCode, calculator.getCountryCode());
		assertEquals(expectedTaxRate, calculator.getTaxRate(), 0);
		assertTrue(calculator.getFixedCosts().isSameAmountAs(expectedFixedCosts));
	}
}
