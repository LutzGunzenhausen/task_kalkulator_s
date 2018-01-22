package de.lutz.task.countryconfig;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

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
@ThreadSafe
public class CountryConfiguration {
	
	private static final Money MINIMUM_FIXED_COSTS = new Money(0, 0);
	
	@Nonnull
	private final String COUNTRY_CODE;
	@Nonnull
	private final String CURRENCY_CODE;
	private final double TAX_RATE;
	@Nonnull
	private final Money FIXED_COSTS;

	public CountryConfiguration(@Nonnull String countryCode, @Nonnull String currencyCode,
			double taxRate, @Nonnull Money fixedCosts) {
		if (taxRate < 0 || taxRate > 1) {
			throw new IllegalArgumentException("The tax-rate must be between 0 and 1.");
		}
		if (fixedCosts.isLesserThan(MINIMUM_FIXED_COSTS)) {
			throw new IllegalArgumentException("The fixed costs amount must be positive.");
		}
		this.COUNTRY_CODE = Objects.requireNonNull(countryCode);
		this.CURRENCY_CODE = Objects.requireNonNull(currencyCode);
		this.TAX_RATE = taxRate;
		this.FIXED_COSTS = Objects.requireNonNull(fixedCosts);
	}
	
	@Nonnull
	public String getCountryCode() {
		return COUNTRY_CODE;
	}
	
	@Nonnull
	public String getCurrencyCode() {
		return CURRENCY_CODE;
	}

	public double getTaxRate() {
		return TAX_RATE;
	}

	@Nonnull
	public Money getFixedCosts() {
		return FIXED_COSTS;
	}
}
