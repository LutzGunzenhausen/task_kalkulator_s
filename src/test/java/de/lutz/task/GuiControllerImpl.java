package de.lutz.task;

import java.util.ArrayList;
import java.util.Collection;

import de.lutz.task.money.Money;
import de.lutz.task.netincome.NetIncomeCalculationService;

public class GuiControllerImpl implements GuiController {
	
	private NetIncomeCalculationService service;
	
	public GuiControllerImpl(NetIncomeCalculationService service) {
		super();
		this.service = service;
	}

	@Override
	public Collection<String> getCountries() {
		Collection<String> currencies = new ArrayList<>();
		currencies.add("UK");
		currencies.add("DE");
		return currencies;
	}

	@Override
	public Money calculateMonthlyNetIncomeInZloty(Money grossIncome, String countryCode) {
		return service.calculateNetIncome(grossIncome, countryCode);
	}
}
