package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
	private static final Logger logger= LoggerFactory.getLogger(PersonController.class);
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public void addPerson(@NonNull @RequestBody Person person) {
		logger.info("Started adding person to DB.");
		personService.insertPerson(person);
	}

	@GetMapping
	public List<Person> getAllPeople() {
		logger.info("Started fetching all the person from DB.");
		return personService.getAllPeople();
	}

	@GetMapping(path = "{id}")
	public Person getPersonByID(@PathVariable("id") UUID id) {
		logger.info("Started fetching the person with id : "+ id +" from DB.");
		return personService.getPersonById(id).orElse(null);
	}

	@DeleteMapping(path = "{id}")
	public void deletePersonById(@PathVariable("id") UUID id) {
		logger.info("Started deleting the person with id : "+ id +".");
		personService.deletePerson(id);
	}

	@PutMapping(path = "{id}")
	public void updatePersonById(@PathVariable("id") UUID id, @NonNull @RequestBody Person personToUpdate) {
		logger.info("Started updating the person with id : "+ id +" in DB.");
		personService.updatePerson(id, personToUpdate);
	}
}
