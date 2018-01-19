package de.lutz.task;

import java.util.ArrayList;
import java.util.Collection;

import de.lutz.task.money.Money;

public class GuiControllerImpl implements GuiController {

	@Override
	public Collection<String> getCountries() {
		Collection<String> currencies = new ArrayList<>();
		currencies.add("GB");
		currencies.add("DE");
		return currencies;
	}

	@Override
	public Money calculateMonthlyNetIncomeInZloty(Money grossIncome, String countryCode) {
		return new Money(13, 37);
	}
}
