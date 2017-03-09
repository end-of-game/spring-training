package org.example.organizer.repository;

import java.util.List;

import org.example.organizer.model.Event;

public interface EventRepository {
	List<Event> findAll();
	
	Event save(Event event);
	
	void delete(Event event);

	void saveAll(List<? extends Event> events);
}
