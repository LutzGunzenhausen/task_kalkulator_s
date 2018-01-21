package de.lutz.task.countryconfig.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.money.Money;

/**
 * Data-class to represent money, as contained in the XML-files to describe the
 * {@link CountryConfiguration}. 
 *
 * @author Christian-PC
 * 2018
 */
class XmlMoney {
	
	@XmlElement(name = "Denominator")
	private int denominator;

	@XmlElement(name = "Cents", required = false)
	private int cents;

	public Money transformConfiguration() {
		return new Money(denominator, cents);
	}
}