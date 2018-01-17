package de.lutz.task.exchange;

import java.io.InputStream;

public interface ExchangeRateReader {

	ExchangeRate readExchangeRate(InputStream stream) throws ReadExchangeRateException;
}
