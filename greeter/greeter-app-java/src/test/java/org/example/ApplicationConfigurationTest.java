package org.example;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.example.greeter.Greeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ApplicationConfiguration.class,
})
public class ApplicationConfigurationTest {
	@Inject
	private Greeter greeter;
	
	@Test
	public void test() {
		String result = greeter.greet("everybody");
		
		assertThat(result, containsString("everybody"));
	}

}
