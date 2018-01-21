package de.lutz.task.countryconfig;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import de.lutz.task.money.Money;

/**
 * This class collects all the configuration, relevant for a chosen country
 * and provides the functionality to calculate the monthly net income for
 * a given gross-income.
 *
 * @author Christian-PC
 * 2018
 */
@Immutable
public class CountryConfiguration {
	
	private static final Money MINIMUM_FIXED_COSTS = new Money(0, 0);
	
	private final String COUNTRY_CODE;
	private String CURRENCY_CODE;
	private final double TAX_RATE;
	private final Money FIXED_COSTS;

	public CountryConfiguration(@Nonnull String countryCode, @Nonnull String currencyCode,
			double taxRate, Money fixedCosts) {
		if (taxRate < 0 || taxRate > 1) {
			throw new IllegalArgumentException("The tax-rate must be between 0 and 1.");
		}
		if (fixedCosts.isLesserThan(MINIMUM_FIXED_COSTS)) {
			throw new IllegalArgumentException("The fixed costs amount must be positive.");
		}
		this.COUNTRY_CODE = Objects.requireNonNull(countryCode);
		this.CURRENCY_CODE = Objects.requireNonNull(currencyCode);
		this.TAX_RATE = taxRate;
		this.FIXED_COSTS = fixedCosts;
	}
	
	public String getCountryCode() {
		return COUNTRY_CODE;
	}
	
	public String getCurrencyCode() {
		return CURRENCY_CODE;
	}

	public double getTaxRate() {
		return TAX_RATE;
	}

	public Money getFixedCosts() {
		return FIXED_COSTS;
	}

	public Money calculateIncome(Money grossIncome) {
		Money result = grossIncome.multiply(1 - TAX_RATE);
		return result.subtract(FIXED_COSTS);
	}
}
