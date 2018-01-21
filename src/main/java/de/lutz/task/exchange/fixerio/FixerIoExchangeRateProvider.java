package de.lutz.task.exchange.fixerio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.ReadExchangeRateException;

/**
 * Implementation of the {@link ExchangeRateProvider} that takes care of reading
 * {@link ExchangeRate}s from the fixer.io service.
 *
 * @author Christian-PC
 * 2018
 */
public class FixerIoExchangeRateProvider implements ExchangeRateProvider {
	
	private final URL url;
	private FixerIoExchangeRateReader reader;
	
	public FixerIoExchangeRateProvider() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		this.url = builder.setBase("PLN").build();
		this.reader = new FixerIoExchangeRateReader();
	}
	
	@Override
	public ExchangeRate readExchangeRate() throws ReadExchangeRateException {
		try (InputStream stream = url.openStream()) {
			return this.reader.readExchangeRate(stream);
		} catch (IOException ex) {
			throw new ReadExchangeRateException(ex);
		}
	}
}
