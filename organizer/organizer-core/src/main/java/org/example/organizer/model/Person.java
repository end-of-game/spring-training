package org.example.organizer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@Embedded
	private Address address;
	
	@ManyToMany(
			mappedBy="participants",
			cascade = { CascadeType.MERGE, CascadeType.PERSIST },
			fetch = FetchType.LAZY)
	private List<Event> participatingEvents = new ArrayList<>();
	
	protected Person() {}
	
	public Person(String firstName, String lastName, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Event> getParticipatingEvents() {
		return Collections.unmodifiableList(participatingEvents);
	}
	
	public void addParticipatingEvent(Event event) {
		participatingEvents.add(event);
	}
	
	public void removeParticipatingEvent(Event event) {
		participatingEvents.remove(event);
	}
}
