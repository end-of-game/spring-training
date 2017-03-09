package org.example.organizer.controller;

public class HelloForm {

	private String message;
	
	public HelloForm() {}
	
	public HelloForm(String lastMessage) {
		this.message = lastMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
