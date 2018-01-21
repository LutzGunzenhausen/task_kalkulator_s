package de.lutz.task.exchange.fixerio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ReadExchangeRateException;

public class FixerIoExchangeRateReaderTest {

	private static FixerIoExchangeRateReader reader;
	private FixerIoUrlBuilder builder;

	@BeforeClass
	public static void initializeClass() {
		reader = new FixerIoExchangeRateReader();
	}

	@Before
	public void initializeTestCase() {
		builder = new FixerIoUrlBuilder();
	}

	@Test
	public void testExchangeRateReading() throws ReadExchangeRateException {
		InputStream stream = getClass().getResourceAsStream("Sample.json");
		ExchangeRate rate = reader.readExchangeRate(stream);
		assertNotNull(rate);
		assertEquals("PLN", rate.getBase());
		LocalDate date = rate.getDate();
		assertDate(date, 2018, 1, 17);
		Map<String, Double> rates = rate.getRates();
		assertNotNull(rates);
		assertFalse(rates.isEmpty());
		assertEquals(3, rates.size());
		assertRate(rates, "GBP", 0.21);
		assertRate(rates, "USD", 0.29);
		assertRate(rates, "EUR", 0.23);
	}

	@Test
	public void testFromHttp() throws ReadExchangeRateException, MalformedURLException, IOException {
		URL url = builder.setBase("PLN"
				).addSymbol("USD").setDate(LocalDate.of(2018, 1, 17)).build();
		
		InputStream is = url.openStream();
		ExchangeRate rate = reader.readExchangeRate(is);
		assertNotNull(rate);
		assertEquals("PLN", rate.getBase());
		LocalDate date = rate.getDate();
		assertDate(date, 2018, 1, 17);
		Map<String, Double> rates = rate.getRates();
		assertNotNull(rates);
		assertFalse(rates.isEmpty());
		assertEquals(1, rates.size());
		assertTrue(rates.containsKey("USD"));
	}
	
	private void assertRate(Map<String, Double> rates, String expectedCountryCode,
			double expectedRate) {
		assertTrue(rates.containsKey(expectedCountryCode));
		double actualRate = rates.get(expectedCountryCode);
		assertEquals(expectedRate, actualRate, 0.0);
	}

	private void assertDate(LocalDate date, int expectedYear, int expectedMonth, int expectedDay) {
		assertNotNull(date);
		assertEquals(expectedYear, date.getYear());
		assertEquals(expectedMonth, date.getMonthValue());
		assertEquals(expectedDay, date.getDayOfMonth());
	}
}
