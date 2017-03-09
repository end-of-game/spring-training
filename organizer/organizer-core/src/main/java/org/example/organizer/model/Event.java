package org.example.organizer.model;

import java.time.LocalDateTime;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Event {
	private Long id;
	
	private String description;
	
	private LocalDateTime beginDateTime;
	
	private LocalDateTime endDateTime;
	
	protected Event() {}
	
	public Event(String description, LocalDateTime begin) {
		this(description, begin, begin);
	}
	
	public Event(String description, LocalDateTime begin, LocalDateTime end) {
		this.description = description;
		this.beginDateTime = begin;
		this.endDateTime = end;
		
		check();
	}
	
	private void check() {
		if (beginDateTime.isAfter(endDateTime)) {
			throw new IllegalArgumentException("End date-time must be after begin date-time");
		}
	}
	
	public Long getId() {
		return id;
	}
	
	protected void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getBeginDateTime() {
		return beginDateTime;
	}

	public void setBeginDateTime(LocalDateTime beginDateTime) {
		this.beginDateTime = beginDateTime;
		
		if (endDateTime != null && beginDateTime.isAfter(endDateTime)) {
			this.endDateTime = beginDateTime;
		}
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
		
		if (beginDateTime != null && endDateTime.isBefore(beginDateTime)) {
			this.beginDateTime = endDateTime;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
