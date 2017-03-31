package org.example.organizer.repository;

import java.util.List;
import java.util.Optional;

import org.example.organizer.model.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

public interface EventRepository extends Repository<Event, Long> {
	List<Event> findAll();
	List<Event> findAll(Sort sort);
	
	Optional<Event> findOne(Long id);
	
	Event save(Event event);
	
	void delete(Event event);
}
