package org.example.organizer.controller;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.example.organizer.model.Event;
import org.example.organizer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/events")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	public void setEventRepository(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public EventResource toResource(Event event) {
		EventResource resource = new EventResource(event);
		
		resource.addLink("delete", fromMethodCall(on(EventController.class).deleteEvent(event.getId())).build().toString());
		
		return resource;
	}
	
	public List<EventResource> toResources(List<Event> event) {
		return event.stream()
			.map(e -> toResource(e))
			.collect(Collectors.toList());
	}
	
	@GetMapping
	public String getEvents(Model model) {
		List<Event> events = eventRepository.findAll();
		
		model.addAttribute("events", toResources(events));
		if (!model.containsAttribute("event")) {
			model.addAttribute("event", new EventResource());
		}
		
		return "event/list";
	}
	
	@PostMapping
	public String createEvent(
			@Valid @ModelAttribute("event") EventResource request,
			BindingResult result) {
		if (result.hasErrors()) {
			return "event/list";
		} else {
			Event event = new Event(
					request.getDescription(),
					LocalDateTime.parse(request.getBeginDateTime()),
					LocalDateTime.parse(request.getEndDateTime()));
			
			event = eventRepository.save(event);
			
			return "redirect:/events";
		}
	}
	
	@PostMapping("/{id}/delete")
	public ModelAndView deleteEvent(@PathVariable Long id) {
		Optional<Event> event = eventRepository.findOne(id);
		
		if (!event.isPresent()) {
			throw new NotFoundException();
		}
		
		eventRepository.delete(event.get());
		
		return new ModelAndView("redirect:/events");
	}
}
