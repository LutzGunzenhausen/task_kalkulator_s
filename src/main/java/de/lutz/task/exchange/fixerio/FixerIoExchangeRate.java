package de.lutz.task.exchange.fixerio;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.lutz.task.exchange.ExchangeRate;

/**
 * Abstract representation of the {@link ExchangeRate} for the way as we
 * get it from the fixer.io service.
 *
 * @author Christian-PC
 * 2018
 */
class FixerIoExchangeRate {
	
	private final String base;
	private final LocalDate date;
	private final Map<String, Double> rates;
	
	@JsonCreator
	public FixerIoExchangeRate(@JsonProperty("base") String base,
			@JsonProperty("date") Date date,
			@JsonProperty("rates") Map<String, Double> rates) {
		this.base = base;
		this.date = new java.sql.Date(date.getTime()).toLocalDate();
		this.rates = rates;
	}

	/**
	 * Transforms this instance's content to a new {@link ExchangeRate}-instance.
	 *
	 * @return the newly created {@link ExchangeRate}.
	 */
	public ExchangeRate transformToExchangeRate() {
		return new ExchangeRate(base, date, rates);
	}
}
