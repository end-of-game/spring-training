package org.example.organizer.api.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;

import org.example.organizer.resource.EventResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventTemplate {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	public ResultActions create(String description, String beginDateTime, String endDateTime) throws Exception {
		EventResource request = new EventResource();
		request.setDescription(description);
		request.setBeginDateTime(beginDateTime);
		request.setEndDateTime(endDateTime);
		
		ResultActions result = mockMvc.perform(
				post("/events")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON));

		return result;
	}
	
	public EventResource getEvent(ResultActions result) throws IOException {
		String content = result.andReturn().getResponse().getContentAsString();
		EventResource response = objectMapper.readValue(content, EventResource.class);
		return response;
	}
	
	public Resources<EventResource> getEvents() throws Exception {
		ResultActions result = mockMvc.perform(get("/events"));
		String content = result.andReturn().getResponse().getContentAsString();
		Resources<EventResource> events = objectMapper.readValue(content, new TypeReference<Resources<EventResource>>() {});
		
		return events;
	}
	
	public ResultActions delete(EventResource event) throws Exception {
		String url = event.getLink(Link.REL_SELF).getHref();
		return mockMvc.perform(MockMvcRequestBuilders.delete(url));
	}
}
