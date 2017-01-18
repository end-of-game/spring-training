package org.example.greeting;

import org.springframework.stereotype.Component;

@Component
public class HelloGreeting implements Greeting {
	private static final String GREETING = "Hello";

	@Override
	public String getGreeting() {
		return GREETING;
	}

}
