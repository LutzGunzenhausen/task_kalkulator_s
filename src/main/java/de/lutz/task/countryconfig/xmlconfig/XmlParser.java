package de.lutz.task.countryconfig.xmlconfig;

import java.io.InputStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.countryconfig.xmlconfig.data.IncomeCalculatorLoaderException;
import de.lutz.task.countryconfig.xmlconfig.data.IncomeConfigurationXml;

public class XmlParser {
	
	public Collection<CountryConfiguration> loadIncomeCalculators(InputStream stream)
			throws IncomeCalculatorLoaderException {
		try {
			IncomeConfigurationXml configuration = getConfiguration(stream);
			return configuration.transformConfiguration();
		} catch (JAXBException ex) {
			throw new IncomeCalculatorLoaderException(ex);
		}
	}
	
	private IncomeConfigurationXml getConfiguration(InputStream stream)
			throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(IncomeConfigurationXml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		IncomeConfigurationXml config =
				(IncomeConfigurationXml) unmarshaller.unmarshal(stream);
		return config;
	}
}
