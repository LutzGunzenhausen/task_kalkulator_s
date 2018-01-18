package de.lutz.task.exchange.fixerio;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ReadExchangeRateException;

class FixerIoExchangeRateReader {

	private ObjectMapper objectMapper;
	
	public FixerIoExchangeRateReader() {
		this.objectMapper = new ObjectMapper();
	}
	
	public ExchangeRate readExchangeRate(InputStream stream)
			throws ReadExchangeRateException {
		try {
			FixerIoExchangeRate rate = objectMapper.readValue(
					stream, FixerIoExchangeRate.class);
			return rate.transformToExchangeRate();
		} catch (IOException ex) {
			throw new ReadExchangeRateException(ex);
		}
	}

}
