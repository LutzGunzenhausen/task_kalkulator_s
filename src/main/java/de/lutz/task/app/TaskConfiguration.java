package de.lutz.task.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.lutz.task.countryconfig.CountryConfigProvider;
import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.xmlconfig.PredefinedXmlCountryConfigProvider;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.fixerio.FixerIoExchangeRateProvider;
import de.lutz.task.netincome.NetIncomeCalculationService;

@Configuration
public class TaskConfiguration {

	@Bean
	public ExchangeRateProvider getExchangeRateProvider() {
		return new FixerIoExchangeRateProvider();
	}
	
	@Bean
	public CountryConfigRegistry getCountryConfigRegistry() {
		List<CountryConfigProvider> providers = new ArrayList<>();
		providers.add(new PredefinedXmlCountryConfigProvider());
		return new CountryConfigRegistry(providers);
	}
	
	@Bean
	public NetIncomeCalculationService getNetIncomeCalculationService(
			CountryConfigRegistry registry, ExchangeRateProvider exchangeRateProvider) {
		return new NetIncomeCalculationService(registry, exchangeRateProvider);
	}
}
