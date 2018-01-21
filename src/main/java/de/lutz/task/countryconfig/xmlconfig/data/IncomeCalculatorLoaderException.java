package de.lutz.task.countryconfig.xmlconfig.data;

import de.lutz.task.countryconfig.CountryConfiguration;

/**
 * Common exception class to sum up problems, that occurred when parsing the some
 * XML-files with the {@link CountryConfiguration}s.
 *
 * @author Christian-PC
 * 2018
 */
public class IncomeCalculatorLoaderException extends Exception {
	private static final long serialVersionUID = -2617075166428766268L;
	
	public IncomeCalculatorLoaderException(Exception cause) {
		super(cause);
	}

}
