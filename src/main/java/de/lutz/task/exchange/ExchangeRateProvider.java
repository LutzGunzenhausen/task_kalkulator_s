package de.lutz.task.exchange;

public interface ExchangeRateProvider {

	ExchangeRate readExchangeRate() throws ReadExchangeRateException;
}
