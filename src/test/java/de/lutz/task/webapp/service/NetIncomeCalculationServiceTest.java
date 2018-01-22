package de.lutz.task.webapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import de.lutz.task.countryconfig.IncomeCalculation;
import de.lutz.task.webapp.TestWebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWebAppConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class NetIncomeCalculationServiceTest {
	
	@Autowired
	private NetIncomeCalculationService service;

	@Test
	public void testController() {
		Model model = new ExtendedModelMap();
		service.calculateNetIncome(model, "100.00", "UK");
		Map<String, Object> map = model.asMap();
		assertValue(map, "numberOfDays", IncomeCalculation.DAYS_PER_MONTH);
		assertValue(map, "dailyGross", "100.00");
		assertValue(map, "monthlyGross", "2200.00");
		assertValue(map, "fixedCosts", "600.00");
		assertValue(map, "taxableIncome", "1600.00");
		assertValue(map, "taxAmount", "400.00");
		assertValue(map, "taxRate", "25%");
		assertValue(map, "monthlyNetIncome", "1200.00");
		assertValue(map, "exchangeRateRate", 0.2112);
		assertValue(map, "monthlyNetIncomePLN", "5681.81");
		assertValue(map, "inputCurrency", "GBP");
		assertValue(map, "inputCountry", "UK");
	}
	
	private void assertValue(Map<String, Object> map, String key, Object expected) {
		assertTrue(map.containsKey(key));
		Object actual = map.get(key);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
