package de.lutz.task.income.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

public class Configuration {
	
	@XmlElement(name = "CountryCode")
	private String countryCode;
	@XmlElement(name = "TaxRate")
	private double taxRate;
	@XmlElement(name = "TaxExemptionAmount")
	private XmlTaxExemptionAmount taxExemption;
	
	public String getCountryCode() {
		return countryCode;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public XmlTaxExemptionAmount getTaxExemption() {
		return taxExemption;
	}

}
