package org.example.organizer.repository.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.organizer.model.Event;
import org.example.organizer.repository.EventRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonEventRepository implements EventRepository {
	private ObjectMapper objectMapper;
	private File store;
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	public void setStore(File store) {
		this.store = store;
	}

	@Override
	public List<Event> findAll() {
		try {
			return objectMapper.readValue(store, new TypeReference<List<Event>>() {});
		} catch (IOException e) {
			throw new RuntimeException("Couldn't read object store", e);
		}
	}

	@Override
	public Event save(Event event) {
		List<Event> storedEvents = new ArrayList<>(findAll());
		storedEvents.add(event);
		
		try {
			objectMapper.writeValue(store, storedEvents);
			return event;
		} catch (IOException e) {
			throw new RuntimeException("Couldn't write to object store", e);
		}
	}

	@Override
	public void delete(Event event) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void saveAll(List<? extends Event> events) {
		List<Event> storedEvents = new ArrayList<>(findAll());
		storedEvents.addAll(events);
		
		try {
			objectMapper.writeValue(store, storedEvents);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't write to object store", e);
		}
		
	}

}
