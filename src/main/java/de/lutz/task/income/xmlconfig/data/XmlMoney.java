package de.lutz.task.income.xmlconfig.data;

import javax.xml.bind.annotation.XmlElement;

import de.lutz.task.money.Money;

class XmlMoney {
	
	@XmlElement(name = "Denominator")
	private int denominator;

	@XmlElement(name = "Cents", required = false)
	private int cents;

	public Money transformConfiguration() {
		return new Money(denominator, cents);
	}
}