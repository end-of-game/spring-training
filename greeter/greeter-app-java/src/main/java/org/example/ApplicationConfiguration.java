package org.example;

import org.example.greeter.Greeter;
import org.example.greeting.HelloGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public Greeter greeter() {
		Greeter greeter = new Greeter();
		greeter.setGreeting(greeting());
		return greeter;
	}
	
	@Bean
	public HelloGreeting greeting() {
		return new HelloGreeting();
	}
}
