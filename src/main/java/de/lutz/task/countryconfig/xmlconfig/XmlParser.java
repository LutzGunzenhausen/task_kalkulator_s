package de.lutz.task.countryconfig.xmlconfig;

import java.io.InputStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.countryconfig.xmlconfig.data.IncomeCalculatorLoaderException;
import de.lutz.task.countryconfig.xmlconfig.data.XmlCountryConfigurations;

class XmlParser {
	
	public Collection<CountryConfiguration> loadIncomeCalculators(InputStream stream)
			throws IncomeCalculatorLoaderException {
		try {
			XmlCountryConfigurations configuration = getConfiguration(stream);
			return configuration.transformConfiguration();
		} catch (JAXBException ex) {
			throw new IncomeCalculatorLoaderException(ex);
		}
	}
	
	private XmlCountryConfigurations getConfiguration(InputStream stream)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(XmlCountryConfigurations.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		XmlCountryConfigurations config =
				(XmlCountryConfigurations) unmarshaller.unmarshal(stream);
		return config;
	}
}
