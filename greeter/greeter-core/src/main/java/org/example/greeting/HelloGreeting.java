package org.example.greeting;

public class HelloGreeting implements Greeting {
	private static final String GREETING = "Hello";

	@Override
	public String getGreeting() {
		return GREETING;
	}

}
