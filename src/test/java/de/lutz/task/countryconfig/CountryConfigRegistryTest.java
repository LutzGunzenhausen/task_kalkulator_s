package de.lutz.task.countryconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.lutz.task.money.Money;

public class CountryConfigRegistryTest {
	
	@Test
	public void testSingleProvider() {
		CountryConfigProvider provider = new CountryConfigProvider() {
			
			@Override
			public Map<String, CountryConfiguration> getConfigurations() {
				Map<String, CountryConfiguration> result = new HashMap<>();
				result.put("DE", new CountryConfiguration("DE", "EUR", 0.5, new Money(100, 0)));
				result.put("PL", new CountryConfiguration("PL", "PLN", 0.8, new Money(200, 0)));
				return result;
			}
		};
		CountryConfigRegistry registry = new CountryConfigRegistry(Collections.singletonList(provider));
		assertRegisry(registry, "ES", "DE", "PL");
	}

	@Test
	public void testSMultipleProviders() {
		CountryConfigProvider provider = new CountryConfigProvider() {
			
			@Override
			public Map<String, CountryConfiguration> getConfigurations() {
				Map<String, CountryConfiguration> result = new HashMap<>();
				result.put("DE", new CountryConfiguration("DE", "EUR", 0.5, new Money(100, 0)));
				result.put("PL", new CountryConfiguration("PL", "PLN", 0.8, new Money(200, 0)));
				return result;
			}
		};

		CountryConfigProvider provider2 = new CountryConfigProvider() {
			
			@Override
			public Map<String, CountryConfiguration> getConfigurations() {
				Map<String, CountryConfiguration> result = new HashMap<>();
				result.put("FR", new CountryConfiguration("FR", "EUR", 0.5, new Money(100, 0)));
				result.put("GB", new CountryConfiguration("GB", "GBP", 0.8, new Money(200, 0)));
				return result;
			}
		};
		List<CountryConfigProvider> providerList = new ArrayList<>();
		providerList.add(provider);
		providerList.add(provider2);
		CountryConfigRegistry registry = new CountryConfigRegistry(providerList);
		assertRegisry(registry, "ES", "DE", "PL", "FR", "GB");
	}
	
	private void assertRegisry(CountryConfigRegistry registry, String unexpectedCountry, String...expectedCountries) {
		for (String expectedCountry : expectedCountries) {
			assertNotNull(registry.getConfigurationForCode(expectedCountry));
		}
		assertNull(registry.getConfigurationForCode(unexpectedCountry));
		
		Collection<String> collection = registry.getCountryCodeCollection();
		assertNotNull(collection);
		assertEquals(expectedCountries.length, collection.size());
		for (String expectedCountry : expectedCountries) {
			assertTrue(collection.contains(expectedCountry));
		}
	}

}
