package org.example.greeter;

import java.util.List;

import org.example.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class Greeter {
	@Autowired
	private Greeting greeting;
	
	@Value("${times}")
	private int times = 1;
	
	public Greeting getGreeting() {
		return greeting;
	}
	
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public void setSomeList(List<String> someStuff) {
		System.out.println(String.join(", ", someStuff));
	}
	
	public String greet(String name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(String.format("%s, %s!%n", greeting.getGreeting(), name));
		}
		return sb.toString();
	}
	
	public void greet() {
		System.out.println(greet("World"));
	}
}
