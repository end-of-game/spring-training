package org.example.organizer.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.example.organizer.model.Event;
import org.example.organizer.model.Person;
import org.example.organizer.repository.EventRepository;
import org.example.organizer.repository.PersonRepository;
import org.example.organizer.resource.EventResource;
import org.example.organizer.resource.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public static EventResource toResource(Event event) {
		EventResource resource = new EventResource(event);
		
		resource.add(linkTo(methodOn(EventController.class).getEvent(event.getId()))
				.withSelfRel());
		resource.add(linkTo(methodOn(EventController.class).getEvents())
				.withRel("events"));
		resource.add(linkTo(methodOn(EventController.class).getParticipants(event.getId()))
				.withRel("participants"));
		
		return resource;
	}
	
	@GetMapping
	public ResponseEntity<?> getEvents() {
		List<Event> events = eventRepository.findAll();
		
		List<EventResource> resources = events.stream()
				.map(e -> toResource(e))
				.collect(Collectors.toList());
		
		Resources<EventResource> resourcesWrapper = new Resources<>(resources);
		
		resourcesWrapper.add(linkTo(methodOn(EventController.class).getEvents())
				.withSelfRel());
		resourcesWrapper.add(linkTo(methodOn(EventController.class).getEvent(null))
				.withRel("event"));
		
		return ResponseEntity.ok(resourcesWrapper);
	}
	
	@PostMapping
	public ResponseEntity<?> createEvent(@Valid @RequestBody EventResource request) {
		Event event = new Event(
				request.getDescription(),
				LocalDateTime.parse(request.getBeginDateTime()),
				LocalDateTime.parse(request.getEndDateTime()));
		
		eventRepository.save(event);
		
		EventResource resource = toResource(event);
		
		return ResponseEntity
				.created(URI.create(resource.getLink(Link.REL_SELF).getHref()))
				.body(resource);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEvent(@PathVariable Long id) {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(toResource(event));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEvent(@PathVariable Long id, @Valid @RequestBody EventResource request) {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			return ResponseEntity.notFound().build();
		}
	    request.updateEvent(event);
		
		eventRepository.save(event);
		
		return ResponseEntity.ok(toResource(event));
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			return ResponseEntity.notFound().build();
		}
		
		eventRepository.delete(event);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/participants")
	public ResponseEntity<?> getParticipants(@PathVariable Long id) {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(toResources(id, event.getParticipants()));
	}
	
	@PostMapping("/{id}/participants")
	public ResponseEntity<?> addParticipant(@PathVariable Long id, @RequestBody PersonResource request) {
		Event event = eventRepository.findOne(id);
		
		if (event == null) {
			return ResponseEntity.notFound().build();
		}
				
		String uriPattern = linkTo(methodOn(PersonController.class).getPerson(null)).withSelfRel().getHref();
		//"http://localhost:8080/persons/{id}"
		String personUri = request.getLink(Link.REL_SELF).getHref();
		
		Long personId = Long.parseLong(new AntPathMatcher().extractUriTemplateVariables(uriPattern, personUri).get("id"));
		
		Person person = personRepository.findOne(personId);
		
		if (person == null) {
			throw new IllegalArgumentException("The person doesn't exist");
		}
		
		event.addParticipant(person);
		
		eventRepository.save(event);
		
		return ResponseEntity.ok(toResources(id, event.getParticipants()));
	}

	private Resources<PersonResource> toResources(Long id, List<Person> participants) {
		Resources<PersonResource> resources = new Resources<>(participants.stream()
				.map(p -> PersonController.toResource(p))
				.collect(Collectors.toList()));
		
		resources.add(linkTo(methodOn(EventController.class).getParticipants(id))
				.withSelfRel());
		resources.add(linkTo(methodOn(EventController.class).getEvent(id))
				.withRel("event"));
		return resources;
	}
}
