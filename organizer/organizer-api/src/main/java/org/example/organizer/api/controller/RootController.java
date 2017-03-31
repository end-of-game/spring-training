package org.example.organizer.api.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
	@GetMapping
	public ResponseEntity<?> root() {
		ResourceSupport resource = new ResourceSupport();
		
		resource.add(linkTo(methodOn(RootController.class).root())
				.withSelfRel());
		resource.add(linkTo(methodOn(EventController.class).getEvents())
				.withRel("events"));
		resource.add(linkTo(methodOn(PersonController.class).getPersons())
				.withRel("persons"));
		
		return ResponseEntity.ok(resource);
	}
}
