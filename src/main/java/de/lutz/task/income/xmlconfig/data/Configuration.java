package de.lutz.task.income.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

import de.lutz.task.income.IncomeCalculator;
import de.lutz.task.money.Money;

class Configuration {
	
	@XmlElement(name = "CountryCode")
	private String countryCode;

	@XmlElement(name = "TaxRate")
	private double taxRate;

	@XmlElement(name = "FixedCost")
	private XmlMoney fixedCostXml;

	public IncomeCalculator transformConfiguratoin() {
		Money fixedCost = fixedCostXml.transformConfiguration();
		return new IncomeCalculator(countryCode, taxRate, fixedCost);
	}
}
