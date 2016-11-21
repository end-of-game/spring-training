package org.example.organizer.model;

import static org.example.organizer.model.Priority.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityTest {
	
	@Test
	public void testPriority() {
		assertThat(LOW, lessThan(HIGH));
	}
}
