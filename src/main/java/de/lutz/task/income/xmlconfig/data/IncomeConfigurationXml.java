package de.lutz.task.income.xmlconfig.data;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import de.lutz.task.income.IncomeCalculator;

@XmlRootElement(name = "IncomeConfiguration")
public class IncomeConfigurationXml {
	
	@XmlElement(name = "Configuration")
	private Collection<Configuration> configurations;

	public Collection<IncomeCalculator> transformConfiguration() {
		Collection<IncomeCalculator> calculators = new ArrayList<>();
		if (this.configurations != null) {
			configurations.forEach((c) -> calculators.add(c.transformConfiguratoin()));
		}
		return calculators;
	}
}
