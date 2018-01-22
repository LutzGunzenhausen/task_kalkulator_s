package de.lutz.task.countryconfig;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Registry to collect all the {@link CountryConfiguration}s and give central
 * access to them.
 *
 * @author Christian-PC
 * 2018
 */
@Immutable
@ThreadSafe
public class CountryConfigRegistry {
	
	private Map<String, CountryConfiguration> configurations;
	
	public CountryConfigRegistry(List<CountryConfigProvider> providers) {
		this.configurations = new HashMap<>();
		for (CountryConfigProvider provider : providers) {
			this.configurations.putAll(provider.getConfigurations());
		}
	}
	
	public CountryConfiguration getConfigurationForCode(String countryCode) {
		return this.configurations.get(countryCode);
	}
	
	public Collection<String> getCountryCodeCollection() {
		return Collections.unmodifiableCollection(configurations.keySet());
	}
}
