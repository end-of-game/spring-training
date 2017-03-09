package com.example.organizer.batch;

import java.time.LocalDateTime;

import org.example.organizer.model.Event;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EventFieldSetMapper implements FieldSetMapper<Event> {

	@Override
	public Event mapFieldSet(FieldSet fields) throws BindException {
		String description = fields.readString("description");
		String beginDateString = fields.readString("beginDateTime");
		String endDateString = fields.readString("endDateTime");
		
		LocalDateTime beginDate = LocalDateTime.parse(beginDateString);
		LocalDateTime endDate = LocalDateTime.parse(endDateString);
		
		return new Event(description, beginDate, endDate);
	}

}
