package de.lutz.task.income.xmlconfig;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.lutz.task.income.IncomeCalculator;
import de.lutz.task.income.xmlconfig.data.IncomeConfigurationXml;

public class XmlParser {
	
	public static void main(String[] args) throws Exception {
		InputStream stream = IncomeCalculator.class.getResourceAsStream("BasicIncomeConfiguration.xml");
		new XmlParser().getConfiguration(stream);
	}
	
	public IncomeConfigurationXml getConfiguration(InputStream stream) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(IncomeConfigurationXml.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		IncomeConfigurationXml config = (IncomeConfigurationXml) unmarshaller.unmarshal(stream);
		return config;
	}
}
