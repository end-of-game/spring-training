package org.example;

import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ApplicationConfiguration.class,
})
public class PrototypeScopeTest {
	@Inject
	@Named("greeter1")
	private Greeter greeter1;
	
	@Inject
	@Named("greeter2")
	private Greeter greeter2;
	
	public void test() {
		Greeting greeting1 = greeter1.getGreeting();
		Greeting greeting2 = greeter2.getGreeting();
		
		Assert.assertThat(greeting1, not(sameInstance(greeting2)));
	}
}
