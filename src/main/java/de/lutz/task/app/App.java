package de.lutz.task.app;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.exchange.ExchangeRateProvider;

public class App {
	
	@Resource(name = "provider")
	private ExchangeRateProvider provider;
	
	@Resource(name = "registry")
	private CountryConfigRegistry registry;

	public void start() {
//		NetIncomeCalculationService service = new NetIncomeCalculationService(
//				registry, provider);
//		GuiController controller = new GuiControllerImpl(service);
//		JFrame frame = new TestGUI(controller);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
	}
	
	@Autowired
	public void setProvider(ExchangeRateProvider provider) {
		this.provider = provider;
	}
	
	@Autowired
	public void setRegistry(CountryConfigRegistry registry) {
		this.registry = registry;
	}
}
