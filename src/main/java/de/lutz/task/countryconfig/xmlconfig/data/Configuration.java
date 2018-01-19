package de.lutz.task.countryconfig.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.money.Money;

class Configuration {
	
	@XmlElement(name = "CountryCode")
	private String countryCode;
	
	@XmlElement(name = "CurrencyCode")
	private String currencyCode;

	@XmlElement(name = "TaxRate")
	private double taxRate;

	@XmlElement(name = "FixedCost")
	private XmlMoney fixedCostXml;

	public CountryConfiguration transformConfiguratoin() {
		Money fixedCost = fixedCostXml.transformConfiguration();
		return new CountryConfiguration(countryCode, currencyCode, taxRate, fixedCost);
	}
}
