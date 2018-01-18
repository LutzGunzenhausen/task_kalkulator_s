package de.lutz.task.exchange.fixerio;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

public class FixerIoUrlBuilder {
	
	private static final String SYMBOLS_KEY = "symbols";
	private static final String BASE_KEY = "base";
	private static final String ATTRIBUTE_LIST_DELIMITER = ";";
	private static final String VALUE_LIST_DELIMITER = ",";
	private static final String BASE_URL = "https://api.fixer.io/";
	private static final String LATEST_DATE = "latest";
	private static final String PARAMETER_DELIMITER = "?";
	
	private LocalDate date;
	private String base;
	private Collection<String> symbols;
	
	private boolean parametersAdded;
	
	public FixerIoUrlBuilder() {
		this.symbols = new LinkedList<>();
		this.parametersAdded = false;
	}
	
	public FixerIoUrlBuilder setBase(String base) {
		this.base = base;
		return this;
	}
	
	public FixerIoUrlBuilder addSymbol(String symbol) {
		this.symbols.add(symbol);
		return this;
	}
	
	public FixerIoUrlBuilder setDate(LocalDate date) {
		this.date = date;
		return this;
	}
	
	public synchronized URL build() {
		this.parametersAdded = false;
		StringBuilder builder = new StringBuilder(BASE_URL);
		appendDate(builder);
		appendAttribute(builder, BASE_KEY, this.base);
		appendAttributes(builder, SYMBOLS_KEY, this.symbols.toArray(new String[0]));
		
		return buildUrl(builder);
	}

	private void appendDate(StringBuilder builder) {
		if (date == null) {
			builder.append(LATEST_DATE);
		} else {
			builder.append(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}
	
	private void appendAttribute(StringBuilder builder, String KEY, String value) {
		if (value != null) {
			appendAttributes(builder, KEY, value);
		}
	}

	private void appendAttributes(StringBuilder builder, String KEY, String...values) {
		if (values == null || values.length == 0) {
			return;
		}
		
		introduceAttributesIfNecessary(builder);
		appendAttributeValues(builder, KEY, values);
	}

	private void appendAttributeValues(StringBuilder builder, String KEY, String...values) {
		builder.append(KEY);
		builder.append("=");
		for (int i = 0; i < values.length; i++) {
			if (i != 0) {
				builder.append(VALUE_LIST_DELIMITER);
			}
			builder.append(values[i]);
		}
		builder.append(ATTRIBUTE_LIST_DELIMITER);
	}

	private void introduceAttributesIfNecessary(StringBuilder builder) {
		if (!parametersAdded) {
			builder.append(PARAMETER_DELIMITER);
			parametersAdded = true;
		}
	}

	private URL buildUrl(StringBuilder builder) {
		try {
			return new URL(builder.toString());
		} catch (MalformedURLException ex) {
			// We are building the Url here. If this is malformed it's programmer mistake
			// and outside nothing can be done. So simply throw as a RuntimeException.
			throw new RuntimeException(ex);
		}
	}
}
