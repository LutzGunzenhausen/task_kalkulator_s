package de.lutz.task.countryconfig;

import java.util.Map;

public interface CountryConfigProvider {

	Map<String, CountryConfiguration> getConfigurations();

}
