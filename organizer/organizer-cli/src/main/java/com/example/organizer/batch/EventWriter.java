package com.example.organizer.batch;

import java.util.List;

import org.example.organizer.model.Event;
import org.example.organizer.repository.EventRepository;
import org.springframework.batch.item.ItemWriter;

public class EventWriter implements ItemWriter<Event> {
	private EventRepository repository;
	
	public void setRepository(EventRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void write(List<? extends Event> events) throws Exception {
		events.forEach(event -> {
			repository.save(event);
		});
	}
}
