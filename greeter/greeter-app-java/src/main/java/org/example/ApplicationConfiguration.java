package org.example;

import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example")
@PropertySource("config.properties")
public class ApplicationConfiguration {
	@Autowired
	private Greeting greeting;
	
	@Value("${times}")
	private int times;
	
	@Value("${option:2}")
	private int option;
	
	@Bean
	public Greeter greeter1() {
		Greeter greeter = new Greeter();
		greeter.setGreeting(greeting);
		greeter.setTimes(times);
		
		System.out.format("Option is %s%n", option);
		
		return greeter;
	}
}
