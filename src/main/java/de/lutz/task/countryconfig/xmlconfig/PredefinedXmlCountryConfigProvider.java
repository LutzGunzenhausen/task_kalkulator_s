package de.lutz.task.countryconfig.xmlconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.lutz.task.countryconfig.CountryConfigProvider;
import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.countryconfig.xmlconfig.data.IncomeCalculatorLoaderException;

/**
 * Implementation of the {@link CountryConfigProvider}, that provides the
 * {@link CountryConfiguration}s, that should already by default be included in the
 * software.
 *
 * @author Christian-PC
 * 2018
 */
public class PredefinedXmlCountryConfigProvider implements CountryConfigProvider {

	private static final String XML_NAME = "BasicIncomeConfiguration.xml";
	private final String BASE_NAME;
	
	public PredefinedXmlCountryConfigProvider() {
		this(XML_NAME);
	}
	
	public PredefinedXmlCountryConfigProvider(String baseName) {
		this.BASE_NAME = baseName;
	}

	@Override
	public Map<String, CountryConfiguration> getConfigurations() {
		try (InputStream stream = getClass().getResourceAsStream(this.BASE_NAME)) {
			CountryConfigXmlParser parser = new CountryConfigXmlParser();
			Collection<CountryConfiguration> calculators =
					parser.loadIncomeCalculators(stream);
			Map<String, CountryConfiguration> result = new HashMap<>();
			calculators.forEach((c) -> result.put(c.getCountryCode(), c));
			return result;
		} catch (IOException | IncomeCalculatorLoaderException ex) {
			throw new RuntimeException(ex);
		}
	}
}
