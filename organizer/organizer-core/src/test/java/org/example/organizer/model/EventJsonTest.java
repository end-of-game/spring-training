package org.example.organizer.model;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class EventJsonTest {
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@Test
	public void testDeserialize() throws IOException {
		try (InputStream input = new ClassPathResource("event.json").getInputStream()) {
			Event[] event = objectMapper.readValue(input, new TypeReference<Event[]>() {});
			
			assertEquals("Dentist", event[0].getDescription());
			assertEquals(LocalDateTime.of(2016, 1, 1, 15, 0), event[0].getBeginDateTime());
			assertEquals(LocalDateTime.of(2016, 1, 1, 16, 0), event[0].getEndDateTime());
		}
	}
	
	@Test
	public void testSerialize() throws IOException {
		Event event = new Event("Dentist", LocalDateTime.of(2016, 1, 1, 15, 0), LocalDateTime.of(2016, 1, 1, 16, 0));
		
		String result = objectMapper.writeValueAsString(event);
		
		assertThat(result, containsString("Dentist"));
		assertThat(result, containsString("2016-01-01T15:00:00"));
		assertThat(result, containsString("2016-01-01T16:00:00"));
	}
}
