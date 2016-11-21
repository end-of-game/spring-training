package org.example;

import org.example.greeter.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static final String DEFAULT_NAME = "World";
	
	private static final Class<?>[] CONFIGURATIONS = {
			ApplicationConfiguration.class,
	};
	
	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CONFIGURATIONS)) {
			String name = DEFAULT_NAME;
			
			if (args.length > 0) {
				name = args[0];
			}
			
			Greeter greeter = ctx.getBean(Greeter.class);
			
			System.out.println(greeter.greet(name));
		}
	}

}
