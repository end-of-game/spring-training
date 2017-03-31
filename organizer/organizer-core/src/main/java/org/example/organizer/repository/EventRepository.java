package org.example.organizer.repository;

import java.util.List;

import org.example.organizer.model.Event;
import org.springframework.data.repository.Repository;

public interface EventRepository extends Repository<Event, Long> {
	List<Event> findAll();
	
	Event findOne(Long id);
	
	Event save(Event event);
	
	void delete(Event event);
}
