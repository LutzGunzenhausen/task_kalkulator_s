package de.lutz.task.countryconfig.xmlconfig;

import java.util.Map;

import org.junit.Test;

import de.lutz.task.countryconfig.CountryConfiguration;
import static org.junit.Assert.*;

public class PredefinedXmlCountryConfigProviderTest {
	
	@Test
	public void basicTest() {
		PredefinedXmlCountryConfigProvider provider = new PredefinedXmlCountryConfigProvider("OneEntry.xml");
		Map<String, CountryConfiguration> configurations = provider.getConfigurations();
		assertNotNull(configurations);
		assertEquals(1, configurations.size());
		assertTrue(configurations.containsKey("UK"));
	}
}
