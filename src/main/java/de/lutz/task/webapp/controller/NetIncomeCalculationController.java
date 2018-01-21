package de.lutz.task.webapp.controller;

import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.lutz.task.money.Money;
import de.lutz.task.money.MoneyLayout;
import de.lutz.task.netincome.NetIncomeCalculationService;

@Controller
@RequestMapping("/index")
public class NetIncomeCalculationController {
	
	private static final int DENOMINATOR_INDEX = 0;
	private static final int CENTS_INDEX = 1;
	
	private NetIncomeCalculationService service;
	private MoneyLayout layout;
	
	@Autowired
	public NetIncomeCalculationController(NetIncomeCalculationService service) {
		this.service = service;
		this.layout = new MoneyLayout();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String greeting(@RequestParam(value="netIncome", required=true) String dailyGrossIncome,
    		@RequestParam(value="country", required=true) String countryCode,
    		Model model) {
		Money money = extractMoneyFromInput(dailyGrossIncome);
        Money netIncome = service.calculateNetIncome(money, countryCode);
        String result = layout.format(netIncome);
        model.addAttribute("result", result);
        putTransactionSpecificData(model, countryCode, money);
        return "/index";
    }
	
	private void putTransactionSpecificData(Model model, String countryCode, Money dailyGrossIncome) {
		model.addAttribute("dailyGross", layout.format(dailyGrossIncome));
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		String percentage = percentFormat.format(this.service.getTaxRateForCountry(countryCode));
		model.addAttribute("taxRate", percentage);
		Money fixedCosts = this.service.getFixedCostsForCountry(countryCode);
		model.addAttribute("fixedCosts", layout.format(fixedCosts));
		model.addAttribute("inputCurrency", this.service.getCurrencyForCountry(countryCode));
		model.addAttribute("countryList", service.getCountryCodeCollection());
		model.addAttribute("inputCountry", countryCode);
		
	}

	private Money extractMoneyFromInput(String dailyGrossIncome) {
		// here we are pretty safe to make the transformations, as we took care at the
		// form that no invalid input is created, hence we would not reach here if the
		// user enters crap.
		String[] parts = dailyGrossIncome.split("[.]");
		int denominator = Integer.parseInt(parts[DENOMINATOR_INDEX]);
		int cents = 0;
		if (parts.length == 2) {
			cents = Integer.parseInt(parts[CENTS_INDEX]);
		}
		Money money = new Money(denominator, cents);
		return money;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("countryList", service.getCountryCodeCollection());
		return "/index";
	}
}
