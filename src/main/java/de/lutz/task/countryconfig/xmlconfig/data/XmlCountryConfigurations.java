package de.lutz.task.countryconfig.xmlconfig.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.lutz.task.countryconfig.CountryConfiguration;

/**
 * Data-class to represent a set of country configurations via xml.
 *
 * @author Christian-PC
 * 2018
 */
@XmlRootElement(name = "IncomeConfiguration")
public class XmlCountryConfigurations {
	
	@XmlElement(name = "Configuration")
	private Collection<XmlCountryConfiguration> configurations;

	public Collection<CountryConfiguration> transformConfiguration() {
		Collection<CountryConfiguration> calculators = new ArrayList<>();
		if (this.configurations != null) {
			configurations.forEach((c) -> calculators.add(c.transformConfiguratoin()));
		}
		return calculators;
	}
}
