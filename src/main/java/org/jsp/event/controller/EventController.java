package org.jsp.event.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.event.entity.Event;
import org.jsp.event.entity.EventStatus;
import org.jsp.event.repository.EventRepository;
import org.jsp.event.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	@Autowired
	EventRepository repository;

	@GetMapping("/hi")
	public String hi() {
		return "Hellow From Event App";
	}
	
	@PostMapping("/events")
	public ResponseStructure<Event> saveEvent(@RequestBody Event event) {
		event=repository.save(event);
		 ResponseStructure<Event> structure=new ResponseStructure<Event>();
		 structure.setHttpStatus(200);
		 structure.setMessage("Event Saved succuessfully");
		 structure.setBody(event);
		 return structure;
		
	}
	
	@GetMapping("/events")
	public ResponseStructure<List<Event>> findAllEvents(){
		List<Event> el= repository.findAll();
		
		if(el.isEmpty()) {
			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
			structure.setHttpStatus(400);
			structure.setMessage(" OOPS..!  No Events found Succussfully");
			structure.setBody(el);
			return structure;
		}
			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
			structure.setHttpStatus(200);
			structure.setMessage("Oh...!   All Events found Succussfully");
			structure.setBody(el);
			return structure;
	}
	
	@GetMapping("/events/{id}")
	public ResponseStructure<Event> findEventById(@PathVariable int id) {
		Optional<Event> optional=repository.findById(id);
		
		ResponseStructure<Event> structure=new ResponseStructure<>();
		if(optional.isEmpty()) {
			
			structure.setHttpStatus(404);
			structure.setMessage(" OOPS..!  No Event found By this Id");
			structure.setBody(null);
			return structure;
			
		}
		else {
			structure.setHttpStatus(200);
			structure.setMessage(" Event found");
			structure.setBody(optional.get());
			return structure;
		}
	}
	
	@DeleteMapping("events/{id}")
	public String deleteEventById(@PathVariable int id) {
		Optional<Event> optional=repository.findById(id);
		
		if(optional.isEmpty()) {
			return "Invalid Event id...Unable to Delete";
		}
		 repository.deleteById(id);
		 return "Event Deleted Succusfully";
	}
	
	@GetMapping("events/guest/{guest}")
	public List<Event> findEventsByGuest(@PathVariable String guest){
		return repository.findByGuest(guest);
	}
	@GetMapping("events/title/{title}")
	public List<Event> findEventByTitle(@PathVariable String title){
		return repository.findByTitle(title);
	}
	 
	@GetMapping("events/status/{status}")
	public List<Event> findEventByStatus(@PathVariable EventStatus title){
		return repository.findByStatus(title);
		
	}
	 
	
	
	
}
