package org.example.organizer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.example.organizer.model.Event;

public class EventResource {
	private static final String DATE_TIME_REGEXP = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}";

	@NotNull(message = "not.empty")
	@Pattern(regexp = "^.*\\S.*$", message = "not.empty")
	private String description;
	
	@NotNull(message = "{not.empty}")
	@Pattern(regexp = DATE_TIME_REGEXP, message = "{date.format}")
	private String beginDateTime;

	@NotNull(message = "{not.empty}")
	@Pattern(regexp = DATE_TIME_REGEXP, message = "{date.format}")
	private String endDateTime;
	
	private Map<String, String> links = new HashMap<>();
	
	public EventResource() {}
	
	public EventResource(Event event) {
		this.description = event.getDescription();
		this.beginDateTime = event.getBeginDateTime().toString();
		this.endDateTime = event.getEndDateTime().toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public Map<String, String> getLinks() {
		return links;
	}
	
	public void addLink(String key, String url) {
		links.put(key, url);
	}
}
