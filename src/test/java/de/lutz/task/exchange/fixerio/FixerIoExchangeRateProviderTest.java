package de.lutz.task.exchange.fixerio;

import org.junit.Test;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ReadExchangeRateException;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Map;

public class FixerIoExchangeRateProviderTest {
	
	@Test
	public void generalTest() throws ReadExchangeRateException {
		FixerIoExchangeRateProvider provider = new FixerIoExchangeRateProvider();
		ExchangeRate exchangeRate = provider.readExchangeRate();
		assertNotNull(exchangeRate);
		assertEquals("PLN", exchangeRate.getBase());
		assertEquals(LocalDate.now(), exchangeRate.getDate());
		Map<String, Double> rates = exchangeRate.getRates();
		assertNotNull(rates);
		assertFalse(rates.isEmpty());
		for (String code : rates.keySet()) {
			Double factor = exchangeRate.getConversionFactorForCountry(code);
			assertNotNull(factor);
		}
		assertNull(exchangeRate.getConversionFactorForCountry("SomeRandomString"));
		
	}

}
