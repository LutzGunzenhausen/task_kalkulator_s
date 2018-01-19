package de.lutz.task.netincome;

import java.util.Map;

import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.ReadExchangeRateException;
import de.lutz.task.exchange.fixerio.FixerIoExchangeRateProvider;
import de.lutz.task.money.Money;

public class NetIncomeCalculationService {
	
	private static final int DAYS_PER_MONTH = 22;
	
	private CountryConfigRegistry registry;
	private ExchangeRateProvider exchangeRateProvider;
	
	public NetIncomeCalculationService(
			Map<String, CountryConfiguration> countryConfigurations,
			ExchangeRateProvider exchangeRateProvider) {
		super();
		this.exchangeRateProvider = new FixerIoExchangeRateProvider();
	}

	public Money calculateNetIncome(Money daylyGrossIncome, String countryCode) {
		CountryConfiguration config = registry.getConfigurationForCode(countryCode);

		Money monthlyGrossIncome = daylyGrossIncome.multiply(DAYS_PER_MONTH);
		Money monthlyNetIncome = config.calculateIncome(monthlyGrossIncome);
		try {
			ExchangeRate exchangeRate = exchangeRateProvider.readExchangeRate();
			double conversionFactor = exchangeRate.getConversionFactorForCountry(countryCode);
			return monthlyNetIncome.divide(conversionFactor);
		} catch (ReadExchangeRateException ex) {
			throw new RuntimeException(ex);
		}
	}
}
