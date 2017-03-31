package org.example.organizer.resource;

import java.time.LocalDateTime;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.example.organizer.model.Event;
import org.springframework.hateoas.ResourceSupport;

public class EventResource extends ResourceSupport {
	private String description;

	private String beginDateTime;
	
	private String endDateTime;

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
	
	public void updateEvent(Event event) {
		event.setDescription(this.getDescription());
		event.setBeginDateTime(LocalDateTime.parse(this.getBeginDateTime()));
		event.setEndDateTime(LocalDateTime.parse(this.getEndDateTime()));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof EventResource)) return false;
		
		EventResource other = (EventResource) obj;
		
		return new EqualsBuilder()
				.append(this.description, other.description)
				.append(this.beginDateTime, other.beginDateTime)
				.append(this.endDateTime, other.endDateTime)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(description)
				.append(beginDateTime)
				.append(endDateTime)
				.toHashCode();
	}
}
