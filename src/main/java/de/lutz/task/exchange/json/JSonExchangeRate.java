package de.lutz.task.exchange.json;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.lutz.task.exchange.ExchangeRate;

class JSonExchangeRate {
	
	private final String base;
	private final LocalDate date;
	private final Map<String, Double> rates;
	
	@JsonCreator
	public JSonExchangeRate(@JsonProperty("base") String base,
			@JsonProperty("date") Date date,
			@JsonProperty("rates") Map<String, Double> rates) {
		this.base = base;
		this.date = new java.sql.Date(date.getTime()).toLocalDate();
		this.rates = rates;
	}

	public ExchangeRate transformToExchangeRate() {
		return new ExchangeRate(base, date, rates);
	}
}
