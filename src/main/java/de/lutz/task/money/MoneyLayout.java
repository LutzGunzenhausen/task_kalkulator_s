package de.lutz.task.money;

import java.text.DecimalFormat;

public class MoneyLayout {
	
	private static final String DENOMINATOR_PATTERN = "0";
	private static final String CENTS_PATTERN = "00";

	public String format(Money money) {
		DecimalFormat denominatorFormat = new DecimalFormat(DENOMINATOR_PATTERN);
		DecimalFormat centsFormat = new DecimalFormat(CENTS_PATTERN);
		
		return denominatorFormat.format(money.getDenominator()) + "." + centsFormat.format(money.getCents());
	}
}
