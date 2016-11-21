package org.example;

import org.example.greeter.Greeter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	private static final String DEFAULT_NAME = "World";
	
	private static final String[] CONFIG_LOCATIONS = {
			"classpath*:application-context.xml",
	};

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG_LOCATIONS)) {
			String name = DEFAULT_NAME;
			
			if (args.length > 0) {
				name = args[0];
			}
			
			Greeter greeter = ctx.getBean(Greeter.class);
			
			System.out.println(greeter.greet(name));
		}
	}
}
