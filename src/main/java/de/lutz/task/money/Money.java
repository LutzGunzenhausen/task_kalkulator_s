package de.lutz.task.money;

/**
 * The general representation of money. It offers several methods for calculation
 * in order to take care of problems that might occur when having to round or similar.
 *
 * @author Christian-PC
 * 2018
 */
public class Money {
	
	public static final Money ZERO = new Money(0, 0);
	
	private final int TOTAL_CENTS;
	private final int DENOMINATOR;
	private final int CENTS;
	
	private Money(int totalCents) {
		this.TOTAL_CENTS = totalCents;
		this.DENOMINATOR = totalCents / 100;
		this.CENTS = Math.abs(totalCents % 100);
	}

	public Money(int denominator, int cents) {
		if (cents < 0 || cents > 99) {
			throw new IllegalArgumentException("The cents must be between 0 and 99");
		}
		if (denominator < 0) {
			this.TOTAL_CENTS = denominator * 100 - cents;
		} else {
			this.TOTAL_CENTS = denominator * 100 + cents;
		}
		
		this.DENOMINATOR = denominator;
		this.CENTS = cents;
	}

	public int getDenominator() {
		return DENOMINATOR;
	}

	public int getCents() {
		return CENTS;
	}

	public Money add(Money second) {
		int sum = TOTAL_CENTS + second.TOTAL_CENTS;
		return new Money(sum);
	}

	public Money subtract(Money second) {
		int difference = TOTAL_CENTS - second.TOTAL_CENTS;
		return new Money(difference);
	}

	public Money multiply(double factor) {
		int product = (int) (TOTAL_CENTS * factor);
		return new Money(product);
	}
	
	public Money divide(double factor) {
		return multiply(1.0 / factor);
	}
	
	@Override
	public String toString() {
		return DENOMINATOR + "," + CENTS;
	}
	
	public boolean isGreaterThan(Money other) {
		return TOTAL_CENTS > other.TOTAL_CENTS;
	}

	public boolean isLesserThan(Money other) {
		return TOTAL_CENTS < other.TOTAL_CENTS;
	}
	
	public boolean isSameAmountAs(Money other) {
		return TOTAL_CENTS == other.TOTAL_CENTS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TOTAL_CENTS;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (TOTAL_CENTS != other.TOTAL_CENTS)
			return false;
		return true;
	}
}
