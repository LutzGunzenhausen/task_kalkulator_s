package de.lutz.task.webapp.service;

import java.text.NumberFormat;
import java.util.Collection;

import org.springframework.ui.Model;

import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.CountryConfiguration;
import de.lutz.task.countryconfig.IncomeCalculation;
import de.lutz.task.exchange.ExchangeRate;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.ReadExchangeRateException;
import de.lutz.task.money.Money;
import de.lutz.task.money.MoneyLayout;
import de.lutz.task.webapp.controller.NetIncomeCalculationController;

/**
 * Service class for the {@link NetIncomeCalculationController}, that executes the
 * actual operations of calculating the monthly net income.
 *
 * @author Christian-PC
 * 2018
 */
public class NetIncomeCalculationService {
	
	private static final String INPUT_CURRENCY = "inputCurrency";
	private static final String MONTHLY_NET_INCOME_PLN = "monthlyNetIncomePLN";
	private static final String EXCHANGE_RATE_RATE = "exchangeRateRate";
	private static final String MONTHLY_NET_INCOME = "monthlyNetIncome";
	private static final String TAX_RATE = "taxRate";
	private static final String TAX_AMOUNT = "taxAmount";
	private static final String TAXABLE_INCOME = "taxableIncome";
	private static final String FIXED_COSTS = "fixedCosts";
	private static final String MONTHLY_GROSS = "monthlyGross";
	private static final String DAILY_GROSS = "dailyGross";
	private static final String NUMBER_OF_DAYS = "numberOfDays";
	private static final String INPUT_COUNTRY = "inputCountry";
	public static final String COUNTRY_LIST = "countryList";

	private static final String BASE_CURRENCY = "PLN";
	
	private CountryConfigRegistry registry;
	private ExchangeRateProvider exchangeRateProvider;
	private MoneyLayout layout;
	
	public NetIncomeCalculationService(CountryConfigRegistry registry,
			ExchangeRateProvider exchangeRateProvider) {
		super();
		this.registry = registry;
		this.layout = new MoneyLayout();
		this.exchangeRateProvider = exchangeRateProvider;
	}
	
	public Collection<String> getCountryCodeCollection() {
		return registry.getCountryCodeCollection();
	}

	public void calculateNetIncome(Model model, String dailyGrossIncomeString, String countryCode) {
		exportDefaultData(model, countryCode);
		if (dailyGrossIncomeString == null || dailyGrossIncomeString.trim().isEmpty()) {
			return;
		}
		
		IncomeCalculation calculation =  calculateNetIncome(
				layout.extractMoneyFromInput(dailyGrossIncomeString), countryCode);
		exportResult(model, calculation, countryCode);
	}

	private IncomeCalculation calculateNetIncome(Money dailyGrossIncome, String countryCode) {
		try {
			CountryConfiguration config = registry.getConfigurationForCode(countryCode);
			String symbol = config.getCurrencyCode();
			double exchangeRate = getExchangeRate(symbol);
			
			return new IncomeCalculation(dailyGrossIncome,
					config.getFixedCosts(),
					config.getTaxRate(), symbol, BASE_CURRENCY, exchangeRate);
		} catch (ReadExchangeRateException ex) {
			throw new RuntimeException(ex);
		}
		
	}

	private double getExchangeRate(final String symbol)
			throws ReadExchangeRateException {
		ExchangeRate exchangeRate = exchangeRateProvider.readExchangeRate();
		double conversionFactor = 1.0;
		// If the input was already made for poland there is no need for transformation
		// anymore.
		if (!symbol.equals(BASE_CURRENCY)) {
			conversionFactor = exchangeRate.getConversionFactorForCountry(symbol);
		}
		return conversionFactor;
	}

	private void exportResult(Model model, IncomeCalculation calculation, String countryCode) {
		model.addAttribute(NUMBER_OF_DAYS, IncomeCalculation.DAYS_PER_MONTH);
		model.addAttribute(DAILY_GROSS, format(calculation.getDailyGrossIncome()));
		model.addAttribute(MONTHLY_GROSS, format(calculation.getMonthlyGrossIncome()));
		model.addAttribute(FIXED_COSTS, format(calculation.getCountrySpecificFixedCosts()));
		model.addAttribute(TAXABLE_INCOME, format(calculation.getTaxableIncome()));
		model.addAttribute(TAX_AMOUNT, format(calculation.getTaxAmount()));
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		String percentage = percentFormat.format(calculation.getTaxRate());
		model.addAttribute(TAX_RATE, percentage);
		model.addAttribute(MONTHLY_NET_INCOME, format(calculation.getMonthlyNetIncomeInOriginCurrency()));
		model.addAttribute(EXCHANGE_RATE_RATE, calculation.getExchangeRate());
		model.addAttribute(MONTHLY_NET_INCOME_PLN, format(calculation.getMonthlyNetIncomeInDestinationCurrency()));
		
		model.addAttribute(INPUT_CURRENCY, calculation.getOriginCurrency());
	}

	private void exportDefaultData(Model model, String countryCode) {
		model.addAttribute(COUNTRY_LIST, getCountryCodeCollection());
		model.addAttribute(INPUT_COUNTRY, countryCode);
	}
	
	private String format(Money money) {
		return this.layout.format(money);
	}
}
