package de.lutz.task.income;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import de.lutz.task.money.Money;

@Immutable
public class IncomeCalculator {
	
	private static final Money MINIMUM_FIXED_COSTS = new Money(0, 0);
	
	private final String COUNTRY_CODE;
	private final double TAX_RATE;
	private final Money FIXED_COSTS;

	public IncomeCalculator(@Nonnull String countryCode, double taxRate, Money fixedCosts) {
		if (taxRate < 0 || taxRate > 1) {
			throw new IllegalArgumentException("The tax-rate must be between 0 and 1.");
		}
		if (fixedCosts.isLesserThan(MINIMUM_FIXED_COSTS)) {
			throw new IllegalArgumentException("The fixed costs amount must be positive.");
		}
		this.COUNTRY_CODE = Objects.requireNonNull(countryCode);
		this.TAX_RATE = taxRate;
		this.FIXED_COSTS = fixedCosts;
	}
	
	public String getCountryCode() {
		return COUNTRY_CODE;
	}

	public double getTaxRate() {
		return TAX_RATE;
	}

	public Money getFixedCosts() {
		return FIXED_COSTS;
	}

	public Money calculateIncome(Money money) {
		Money result = money.multiply(1 - TAX_RATE);
		return result.subtract(FIXED_COSTS);
	}
}
