package org.example.greeter;

import org.example.greeting.Greeting;

public class Greeter {
	private Greeting greeting;
	
	public Greeting getGreeting() {
		return greeting;
	}
	
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
	
	public String greet(String name) {
		return String.format("%s, %s!", greeting.getGreeting(), name);
	}
}
