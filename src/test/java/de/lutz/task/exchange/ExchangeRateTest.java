package de.lutz.task.exchange;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.lutz.task.exchange.ExchangeRate;

import static org.junit.Assert.*;

public class ExchangeRateTest {
	
	@Test
	public void generalTest() {
		Map<String, Double> rates = new HashMap<>();
		rates.put("EUR", 0.25);
		rates.put("AUD", 0.36);
		ExchangeRate rate = new ExchangeRate("PLN", LocalDate.of(2018, 1, 17), rates);
		assertEquals("PLN", rate.getBase());
		assertEquals(LocalDate.of(2018, 1, 17), rate.getDate());
		assertEquals(0.25, rate.getConversionFactorForCountry("EUR"), 0.0);
		assertEquals(0.36, rate.getConversionFactorForCountry("AUD"), 0.0);
		assertNull(rate.getConversionFactorForCountry("GBP"));
	}

}
