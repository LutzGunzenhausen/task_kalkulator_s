package de.lutz.task.income.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

public class XmlTaxExemptionAmount {
	
	@XmlElement(name = "Denominator")
	private int denominator;
	@XmlElement(name = "Cents", required = false)
	private int cents;
}