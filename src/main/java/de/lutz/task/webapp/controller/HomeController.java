package de.lutz.task.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.lutz.task.money.Money;
import de.lutz.task.netincome.NetIncomeCalculationService;

@Controller
@RequestMapping("/index")
public class HomeController {
	
	private NetIncomeCalculationService service;
	
	@Autowired
	public HomeController(NetIncomeCalculationService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String greeting(@RequestParam(value="netIncome", required=false, defaultValue="0,0") String dailyGrossIncome,
    		@RequestParam(value="country", required=false, defaultValue="UK") String countryCode,
    		Model model) {
		String[] parts = dailyGrossIncome.split(",");
		int denominator = Integer.parseInt(parts[0]);
		int cents = 0;
		if (parts.length == 2) {
			cents = Integer.parseInt(parts[1]);
		}
		Money money = new Money(denominator, cents);
        Money netIncome = service.calculateNetIncome(money, countryCode);
        model.addAttribute("result", netIncome.toString());
        
        
        return "/result";
    }

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("countryList", service.getCountryCodeCollection());
		return "/index";
	}
}
