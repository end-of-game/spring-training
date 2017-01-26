package org.example.organizer.model;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class EventToStringTest {
	@Test
	public void test() {
		Event event = new Event("Lunch", LocalDateTime.of(2017, 1, 1, 12, 0, 0), LocalDateTime.of(2017, 1, 1, 13, 0, 0));
		
		String result = event.toString();
		
		System.out.println(result);
		assertThat(result, containsString("description=Lunch"));
	}
}
