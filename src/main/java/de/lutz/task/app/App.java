package de.lutz.task.app;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.exchange.ExchangeRateProvider;

public class App {
	
	@Resource(name = "provider")
	private ExchangeRateProvider provider;
	
	@Resource(name = "registry")
	private CountryConfigRegistry registry;

	public void start() {
		
	}
	
	@Autowired
	public void setProvider(ExchangeRateProvider provider) {
		this.provider = provider;
	}
	
	@Autowired
	public void setRegistry(CountryConfigRegistry registry) {
		this.registry = registry;
	}
}
