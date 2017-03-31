package org.example.organizer.model;

import javax.persistence.Embeddable;

import org.apache.commons.lang.StringUtils;

@Embeddable
public class Address {
	private String firstLine;
	private String secondLine;
	
	private String postCode;
	private String town;
	private String country;
	
	protected Address() {}
	
	public Address(String firstLine, String secondLine, String postCode, String town, String country) {

		if (StringUtils.isBlank(firstLine)) {
			throw new IllegalArgumentException("firstLine cannot be empty");
		}
		this.firstLine = firstLine;
		this.secondLine = secondLine;
		this.postCode = postCode;
		this.town = town;
		this.country = country;
	}
	
	public Address(Address address) {
		this(address.firstLine, address.secondLine, address.postCode, address.town, address.country);
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
