package de.lutz.task.income;

import javax.annotation.concurrent.Immutable;

import de.lutz.task.money.ComparisonResult;
import de.lutz.task.money.Money;

@Immutable
public class IncomeCalculator {
	
	private static final Money MINIMUM_EXEMPTION_AMOUNT = new Money(0, 0);
	
	private final double TAX_RATE;
	private final Money TAX_EXEMPTION_AMOUNT;

	public IncomeCalculator(double taxRate, Money taxExemptionAmount) {
		if (taxRate < 0 || taxRate > 1) {
			throw new IllegalArgumentException("The tax-rate must be between 0 and 1.");
		}
		if (taxExemptionAmount.compareWith(MINIMUM_EXEMPTION_AMOUNT) == ComparisonResult.LESSER) {
			throw new IllegalArgumentException("The tax-exemption amount must be positive.");
		}
		this.TAX_RATE = taxRate;
		this.TAX_EXEMPTION_AMOUNT = taxExemptionAmount;
	}

	public double getTaxRate() {
		return TAX_RATE;
	}

	public Money getTaxExemptionAmount() {
		return TAX_EXEMPTION_AMOUNT;
	}

	public Money calculateIncome(Money money) {
		Money result = null;
		ComparisonResult comparisonResult = money.compareWith(TAX_EXEMPTION_AMOUNT);
		if (comparisonResult == ComparisonResult.BIGGER) {
			Money taxableAmount = money.subtract(TAX_EXEMPTION_AMOUNT);
			Money tax = taxableAmount.multiply(TAX_RATE);
			result = money.subtract(tax);
		} else {
			result = money;
		}
		
		return result;
	}
}
