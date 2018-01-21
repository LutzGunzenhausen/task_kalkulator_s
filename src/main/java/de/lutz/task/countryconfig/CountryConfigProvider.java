package de.lutz.task.countryconfig;

import java.util.Map;

/**
 * Implementations of this interface are used by the {@link CountryConfigRegistry} in
 * order to load all the {@link CountryConfiguration}s, that are known to the system.
 * 
 * @author Christian-PC
 * 2018
 */
public interface CountryConfigProvider {

	/**
	 * Returns the configurations provided by this class in a map.
	 *
	 * @return the configurations in a map, where the key is the country-code, e.g.
	 * DE for Germany. The value is always the configuration itself.
	 */
	Map<String, CountryConfiguration> getConfigurations();

}
