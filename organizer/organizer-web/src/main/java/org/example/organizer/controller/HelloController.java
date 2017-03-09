package org.example.organizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	private int counter = 0;
	
	private String lastMessage = "";
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(Model model) {
		//model.addAttribute("greeting", greeting);
		model.addAttribute("counter", counter++);
		model.addAttribute("form", new HelloForm(lastMessage));
		
		return "hello/form";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public String postHello(Model model, HelloForm form) {
		model.addAttribute("form", form);
		
		lastMessage = form.getMessage();
		
		return "hello/form";
	}
}
