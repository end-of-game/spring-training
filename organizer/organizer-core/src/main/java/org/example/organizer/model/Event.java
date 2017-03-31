package org.example.organizer.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Event {
	@Id
	@GeneratedValue
	private Long id;
	
	private String description;

	private LocalDateTime beginDateTime;
	
	private LocalDateTime endDateTime;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(
			joinColumns = @JoinColumn(name = "EVENT_ID"),
			inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
	private List<Person> participants = new ArrayList<>();
	
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
	
	public List<Person> getParticipants() {
		return Collections.unmodifiableList(participants);
	}
	
	public void addParticipant(Person person) {
		participants.add(person);
		person.addParticipatingEvent(this);
	}
	
	public void removeParticipant(Person person) {
		participants.remove(person);
		person.removeParticipatingEvent(this);
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
