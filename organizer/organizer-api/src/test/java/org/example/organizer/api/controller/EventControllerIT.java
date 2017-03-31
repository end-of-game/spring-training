package org.example.organizer.api.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.organizer.api.test.EventTemplate;
import org.example.organizer.resource.EventResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
public class EventControllerIT {
	private static final String END_DATE = "2017-01-01T17:00:01";

	private static final String BEGIN_DATE = "2017-01-01T16:00:01";

	private static final String DESCRIPTION = "Have fun!";

	@Autowired
	private EventTemplate eventTemplate;
	
	@Test
	public void testCreateEvent() throws Exception {
		ResultActions result = eventTemplate.create(DESCRIPTION, BEGIN_DATE, END_DATE);
		
		result.andExpect(status().isCreated());
		result.andExpect(header().string("Location", containsString("/events/")));
		
		EventResource event = eventTemplate.getEvent(result);
		
		assertEquals(DESCRIPTION, event.getDescription());
		assertEquals(BEGIN_DATE, event.getBeginDateTime());
		assertEquals(END_DATE, event.getEndDateTime());
	}
	
	@Test
	@Ignore
	public void testDeleteEvent() throws Exception {
		ResultActions result = eventTemplate.create(DESCRIPTION, BEGIN_DATE, END_DATE);
		EventResource event = eventTemplate.getEvent(result);
		
		result = eventTemplate.delete(event);
		result.andExpect(status().isNoContent());
		
		Resources<EventResource> events = eventTemplate.getEvents();
		
		assertThat(events.getContent(), empty());
	}
}
