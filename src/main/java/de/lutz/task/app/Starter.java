package de.lutz.task.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.lutz.task.money.Money;
import de.lutz.task.netincome.NetIncomeCalculationService;

public class Starter {
	
	private static final String CONFIGURATION_XML = "de/lutz/task/app/AppConfiguration.xml";

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
			ctx.register(TaskConfiguration.class);
			ctx.refresh();
			NetIncomeCalculationService bean = ctx.getBean(NetIncomeCalculationService.class);
			NetIncomeCalculationService bean1 = ctx.getBean(NetIncomeCalculationService.class);
			Money income = bean.calculateNetIncome(new Money(100, 00), "UK");
			System.out.println(income);
		}
		
//		try (ClassPathXmlApplicationContext context =
//				new ClassPathXmlApplicationContext(CONFIGURATION_XML)) {
//			App app = context.getBean(App.class);
//			app.start();
//		}
	}
}
