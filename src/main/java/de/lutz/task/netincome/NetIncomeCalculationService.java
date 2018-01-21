package de.lutz.task.netincome;

import java.util.Collection;

import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.ReadExchangeRateException;
import de.lutz.task.exchange.fixerio.FixerIoExchangeRateProvider;
import de.lutz.task.money.Money;

public class NetIncomeCalculationService {
	
	private static final int DAYS_PER_MONTH = 22;
	private static final String BASE_CURRENCY = "PLN";
	
	private CountryConfigRegistry registry;
	private ExchangeRateProvider exchangeRateProvider;
	
	public NetIncomeCalculationService(CountryConfigRegistry registry,
			ExchangeRateProvider exchangeRateProvider) {
		super();
		this.registry = registry;
		this.exchangeRateProvider = new FixerIoExchangeRateProvider();
	}

	public Money calculateNetIncome(Money daylyGrossIncome, String countryCode) {
		CountryConfiguration config = registry.getConfigurationForCode(countryCode);

		Money monthlyGrossIncome = daylyGrossIncome.multiply(DAYS_PER_MONTH);
		Money monthlyNetIncome = config.calculateIncome(monthlyGrossIncome);
		try {
			ExchangeRate exchangeRate = exchangeRateProvider.readExchangeRate();
			final String symbol = config.getCurrencyCode();
			double conversionFactor = 1.0;
			if (!symbol.equals(BASE_CURRENCY)) {
				conversionFactor = exchangeRate.getConversionFactorForCountry(symbol);
			}
			return monthlyNetIncome.divide(conversionFactor);
		} catch (ReadExchangeRateException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public Collection<String> getCountryCodeCollection() {
		return registry.getCountryCodeCollection();
	}
}
