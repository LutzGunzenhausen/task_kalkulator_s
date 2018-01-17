package de.lutz.task.income.xmlconfig;

import java.io.InputStream;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.lutz.task.income.IncomeCalculator;
import de.lutz.task.income.xmlconfig.data.IncomeCalculatorLoaderException;
import de.lutz.task.income.xmlconfig.data.IncomeConfigurationXml;

public class XmlParser {
	
	public Collection<IncomeCalculator> loadIncomeCalculators(InputStream stream)
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
