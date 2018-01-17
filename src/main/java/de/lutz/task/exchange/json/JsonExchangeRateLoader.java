package de.lutz.task.exchange.json;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateReader;
import de.lutz.task.exchange.ReadExchangeRateException;

public class JsonExchangeRateLoader implements ExchangeRateReader {
	
	private ObjectMapper objectMapper;
	
	public JsonExchangeRateLoader() {
		this.objectMapper = new ObjectMapper();
	}
	
	@Override
	public ExchangeRate readExchangeRate(InputStream stream)
			throws ReadExchangeRateException {
		try {
			JSonExchangeRate rate = objectMapper.readValue(stream, JSonExchangeRate.class);
			return rate.transformToExchangeRate();
		} catch (IOException ex) {
			throw new ReadExchangeRateException(ex);
		}
	}
}
