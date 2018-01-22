package de.lutz.task.webapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.lutz.task.countryconfig.CountryConfigProvider;
import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.xmlconfig.PredefinedXmlCountryConfigProvider;
import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.ReadExchangeRateException;
import de.lutz.task.webapp.service.NetIncomeCalculationService;

@Configuration
public class TestWebAppConfiguration {
	
	@Bean
	public ExchangeRateProvider getExchangeRateProvider() {
		return new ExchangeRateProvider() {
			
			@Override
			public ExchangeRate readExchangeRate() throws ReadExchangeRateException {
				Map<String, Double> rates = new HashMap<>();
				rates.put("GBP", 0.2112);
				return new ExchangeRate("PLN", LocalDate.now(), rates);
			}
		};
	}
	
	@Bean
	public CountryConfigRegistry getCountryConfigRegistry() {
		List<CountryConfigProvider> providers = new ArrayList<>();
		providers.add(new PredefinedXmlCountryConfigProvider("OneEntry.xml"));
		return new CountryConfigRegistry(providers);
	}
	
	@Bean
	public NetIncomeCalculationService getNetIncomeCalculationService(
			CountryConfigRegistry registry, ExchangeRateProvider exchangeRateProvider) {
		return new NetIncomeCalculationService(registry, exchangeRateProvider);
	}

}
