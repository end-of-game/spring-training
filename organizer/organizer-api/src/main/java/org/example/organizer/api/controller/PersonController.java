package org.example.organizer.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.example.organizer.model.Address;
import org.example.organizer.model.Event;
import org.example.organizer.model.Person;
import org.example.organizer.repository.PersonRepository;
import org.example.organizer.resource.EventResource;
import org.example.organizer.resource.PersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
	public static PersonResource toResource(Person person) {
		PersonResource resource = new PersonResource(person);
		
		resource.add(linkTo(methodOn(PersonController.class).getPerson(person.getId()))
				.withSelfRel());
		resource.add(linkTo(methodOn(PersonController.class).getPersons())
				.withRel("persons"));
		
		return resource;
	}
	
	@GetMapping
	public ResponseEntity<?> getPersons() {
		List<Person> persons = personRepository.findAll();
		
		List<PersonResource> resources = persons.stream()
				.map(e -> toResource(e))
				.collect(Collectors.toList());
		
		Resources<PersonResource> resourcesWrapper = new Resources<>(resources);
		
		resourcesWrapper.add(linkTo(methodOn(PersonController.class).getPersons())
				.withSelfRel());
		resourcesWrapper.add(linkTo(methodOn(PersonController.class).getPerson(null))
				.withRel("person"));
		
		return ResponseEntity.ok(resourcesWrapper);
	}
	
	@PostMapping
	public ResponseEntity<?> createPerson(@Valid @RequestBody PersonResource request) {
		Person person = new Person(
				request.getFirstName(),
				request.getLastName(),
				new Address(request.getAddress()));
		
		personRepository.save(person);
		
		PersonResource resource = toResource(person);
		
		return ResponseEntity
				.created(URI.create(resource.getLink(Link.REL_SELF).getHref()))
				.body(resource);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPerson(@PathVariable Long id) {
		Person person = personRepository.findOne(id);
		
		if (person == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(toResource(person)); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		Person person = personRepository.findOne(id);
		
		if (person == null) {
			return ResponseEntity.notFound().build();
		}
		
		personRepository.delete(person);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/participating-events")
	public ResponseEntity<?> getParticipatingEvents(@PathVariable Long id) {
		Person person = personRepository.findOne(id);
		
		if (person == null) {
			return ResponseEntity.notFound().build();
		}

		List<Event> events = person.getParticipatingEvents();
		
		Resources<EventResource> resources = new Resources<>(events.stream()
				.map(e -> EventController.toResource(e))
				.collect(Collectors.toList()));
		
		resources.add(linkTo(methodOn(PersonController.class).getParticipatingEvents(id))
				.withSelfRel());
		
		return ResponseEntity.ok(resources);
	}
}
