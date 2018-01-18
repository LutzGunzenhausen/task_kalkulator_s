package de.lutz.task.exchange;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}
