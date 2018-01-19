package de.lutz.task;

import java.util.Collection;

import de.lutz.task.money.Money;

public interface GuiController {
	
	Collection<String> getCountries();
	
	Money calculateMonthlyNetIncomeInZloty(Money grossIncome, String countryCode);

}
