package de.lutz.task.income.xmlconfig.data;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "IncomeConfiguration")
public class IncomeConfigurationXml {
	
	@XmlElement(name = "Configuration")
	private Collection<Configuration> configurations;
	
	public Collection<Configuration> getConfigurations() {
		return configurations;
	}

}
