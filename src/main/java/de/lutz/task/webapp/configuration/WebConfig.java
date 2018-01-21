package de.lutz.task.webapp.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import de.lutz.task.countryconfig.CountryConfigProvider;
import de.lutz.task.countryconfig.CountryConfigRegistry;
import de.lutz.task.countryconfig.xmlconfig.PredefinedXmlCountryConfigProvider;
import de.lutz.task.exchange.ExchangeRateProvider;
import de.lutz.task.exchange.fixerio.FixerIoExchangeRateProvider;
import de.lutz.task.netincome.NetIncomeCalculationService;

@Configuration
@EnableWebMvc
@ComponentScan("de.lutz.task.webapp")
public class WebConfig implements WebMvcConfigurer {

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public ExchangeRateProvider getExchangeRateProvider() {
		return new FixerIoExchangeRateProvider();
	}
	
	@Bean
	public CountryConfigRegistry getCountryConfigRegistry() {
		List<CountryConfigProvider> providers = new ArrayList<>();
		providers.add(new PredefinedXmlCountryConfigProvider());
		return new CountryConfigRegistry(providers);
	}
	
	@Bean
	public NetIncomeCalculationService getNetIncomeCalculationService(
			CountryConfigRegistry registry, ExchangeRateProvider exchangeRateProvider) {
		return new NetIncomeCalculationService(registry, exchangeRateProvider);
	}
	
}
