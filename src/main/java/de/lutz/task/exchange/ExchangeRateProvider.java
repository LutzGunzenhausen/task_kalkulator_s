package de.lutz.task.exchange;

/**
 * Interface for any kind of service, that provides current exchange-rates.
 *
 * @author Christian-PC
 * 2018
 */
public interface ExchangeRateProvider {

	ExchangeRate readExchangeRate() throws ReadExchangeRateException;
}
