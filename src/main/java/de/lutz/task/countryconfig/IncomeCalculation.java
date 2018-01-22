package de.lutz.task.countryconfig;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import de.lutz.task.money.Money;

/**
 * The general result of the execution of this app.<br />
 * It consists of the results for every step in the calculation process as well
 * as some additional data such as the exchange- or tax-rate, fixed costs, currencies
 * and so on.
 *
 * @author Christian-PC
 * 2018
 */
@Immutable
@ThreadSafe
public class IncomeCalculation {
	
	public static final int DAYS_PER_MONTH = 22;
	
	private final Money dailyGrossIncome;
	private final Money monthlyGrossIncome;
	private final Money countrySpecificFixedCosts;
	private final double taxRate;
	private final Money taxableIncome;
	private final Money taxAmount;
	private final Money monthlyNetIncomeInOriginCurrency;
	private final Money monthlyNetIncomeInDestinationCurrency;
	private final String originCurrency;
	private final String destinationCurrency;
	private final double exchangeRate;
	
	public IncomeCalculation(Money dailyGrossIncome, Money countrySpecificFixedCosts,
			double taxRate, String originCurrency, String destinationCurrency,
			double exchangeRate) {
		this.dailyGrossIncome = dailyGrossIncome;
		this.monthlyGrossIncome = dailyGrossIncome.multiply(DAYS_PER_MONTH);
		this.countrySpecificFixedCosts = countrySpecificFixedCosts;
		this.taxRate = taxRate;
		this.taxableIncome = this.monthlyGrossIncome.subtract(this.countrySpecificFixedCosts);
		
		if (this.taxableIncome.isGreaterThan(Money.ZERO)) {
			this.taxAmount = this.taxableIncome.multiply(this.taxRate);
		} else {
			this.taxAmount = Money.ZERO;
		}
		this.monthlyNetIncomeInOriginCurrency = this.taxableIncome.subtract(this.taxAmount);
		this.monthlyNetIncomeInDestinationCurrency = this.monthlyNetIncomeInOriginCurrency.divide(exchangeRate);
		this.originCurrency = originCurrency;
		this.destinationCurrency = destinationCurrency;
		this.exchangeRate = exchangeRate;
	}
	
	public Money getDailyGrossIncome() {
		return dailyGrossIncome;
	}

	public Money getMonthlyGrossIncome() {
		return monthlyGrossIncome;
	}

	public Money getCountrySpecificFixedCosts() {
		return countrySpecificFixedCosts;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public Money getTaxableIncome() {
		return taxableIncome;
	}

	public Money getTaxAmount() {
		return taxAmount;
	}

	public Money getMonthlyNetIncomeInOriginCurrency() {
		return monthlyNetIncomeInOriginCurrency;
	}
	
	public Money getMonthlyNetIncomeInDestinationCurrency() {
		return monthlyNetIncomeInDestinationCurrency;
	}
	
	public String getOriginCurrency() {
		return originCurrency;
	}
	
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	
	public double getExchangeRate() {
		return exchangeRate;
	}
}
