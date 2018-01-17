package de.lutz.task.income.xmlconfig;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import de.lutz.task.income.IncomeCalculator;
import de.lutz.task.income.xmlconfig.data.IncomeCalculatorLoaderException;
import de.lutz.task.money.Money;

import static org.junit.Assert.*;

public class XmlParserTest {
	
	private static XmlParser parser;
	
	@BeforeClass
	public static void initialize() {
		parser = new XmlParser();
	}
	
	@Test
	public void testEmptyImport() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("Empty.xml");
		Collection<IncomeCalculator> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertTrue(calculators.isEmpty());
	}
	
	@Test
	public void testImportOneEntry() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("OneEntry.xml");
		Collection<IncomeCalculator> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertFalse(calculators.isEmpty());
		assertEquals(1, calculators.size());
		assertCalculator(calculators.iterator().next(), "UK", 0.25, new Money(600, 0));
	}

	@Test
	public void testImportMultipleEntries() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("MultipleEntries.xml");
		Collection<IncomeCalculator> calculators = parser.loadIncomeCalculators(stream);
		assertNotNull(calculators);
		assertFalse(calculators.isEmpty());
		assertEquals(2, calculators.size());
		Iterator<IncomeCalculator> iterator = calculators.iterator();
		assertCalculator(iterator.next(), "UK", 0.25, new Money(600, 0));
		assertCalculator(iterator.next(), "PL", 0.19, new Money(1200, 50));
	}
	
	@Test(expected = IncomeCalculatorLoaderException.class)
	public void testExceptionAtMalformedXml() throws IncomeCalculatorLoaderException {
		InputStream stream = getClass().getResourceAsStream("MalformedXml.xml");
		parser.loadIncomeCalculators(stream);
	}
	
	private void assertCalculator(IncomeCalculator calculator, String expectedCountryCode,
			double expectedTaxRate, Money expectedFixedCosts) {
		assertEquals(expectedCountryCode, calculator.getCountryCode());
		assertEquals(expectedTaxRate, calculator.getTaxRate(), 0);
		assertTrue(calculator.getFixedCosts().isSameAmountAs(expectedFixedCosts));
	}
}
