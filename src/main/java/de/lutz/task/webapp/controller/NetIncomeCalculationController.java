package de.lutz.task.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.lutz.task.webapp.service.NetIncomeCalculationService;

/**
 * Controller, that serves requests from the /index page.
 *
 * @author Christian-PC
 * 2018
 */
@Controller
@RequestMapping("/index")
public class NetIncomeCalculationController {
	
	private NetIncomeCalculationService service;
	
	@Autowired
	public NetIncomeCalculationController(NetIncomeCalculationService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String postDailyGrossIncome(
    		@RequestParam(value="netIncome", required=true) String dailyGrossIncomeString,
    		@RequestParam(value="country", required=true) String countryCode,
    		Model model) {
		service.calculateNetIncome(model, dailyGrossIncomeString, countryCode);
        return "/index";
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) {
		model.addAttribute(NetIncomeCalculationService.COUNTRY_LIST,
				service.getCountryCodeCollection());
		return "/index";
	}
}
