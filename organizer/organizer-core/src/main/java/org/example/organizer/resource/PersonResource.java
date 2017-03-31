package org.example.organizer.resource;

import org.example.organizer.model.Address;
import org.example.organizer.model.Person;
import org.springframework.hateoas.ResourceSupport;

public class PersonResource extends ResourceSupport {
	private String firstName;
	
	private String lastName;
	
	private Address address;
	
	public PersonResource() {}
	
	public PersonResource(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
