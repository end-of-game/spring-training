package org.example;

import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class ApplicationConfiguration {
	@Autowired
	private Greeting greeting;
	
	@Bean
	public Greeter greeter1() {
		Greeter greeter = new Greeter();
		greeter.setGreeting(greeting);
		greeter.setTimes(5);
		return greeter;
	}
}
