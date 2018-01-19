package de.lutz.task.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
	
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("de/lutz/task/app/AppConfiguration.xml");
		App app = context.getBean(App.class);
		app.start();
	}
}
