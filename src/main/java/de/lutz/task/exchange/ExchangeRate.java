package de.lutz.task.exchange;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An instance of this class collects all kind of information about the exchange-rate
 * to some given base. It consists of the date for when the exchange rate was determined
 * and the different rates to make the transformation from some currency to the given base.
 *
 * @author Christian-PC
 * 2018
 */
public class ExchangeRate {

	private final String base;
	private final LocalDate date;
	private final Map<String, Double> rates;
	
	public ExchangeRate(String base, LocalDate date,
			Map<String, Double> rates) {
		this.base = base;
		this.date = date;
		this.rates = new HashMap<>(rates);
	}

	public String getBase() {
		return base;
	}

	public LocalDate getDate() {
		return date;
	}

	public Map<String, Double> getRates() {
		return Collections.unmodifiableMap(this.rates);
	}
	
	public double getConversionFactorForCountry(String countryCode) {
		return this.rates.get(countryCode);
	}
	
}
