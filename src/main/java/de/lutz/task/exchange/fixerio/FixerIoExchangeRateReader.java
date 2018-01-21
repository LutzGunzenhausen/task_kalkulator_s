package de.lutz.task.exchange.fixerio;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ReadExchangeRateException;

/**
 * This class is responsible to extract the {@link ExchangeRate} from
 * a given stream, containing the JSON-data as delivered by the fixer.io
 * service.
 *
 * @author Christian-PC
 * 2018
 */
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
