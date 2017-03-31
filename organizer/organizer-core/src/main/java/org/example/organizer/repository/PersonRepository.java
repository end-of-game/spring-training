package org.example.organizer.repository;

import java.util.List;

import org.example.organizer.model.Person;
import org.springframework.data.repository.Repository;

public interface PersonRepository extends Repository<Person, Long> {
	Person findOne(Long id);
	
	List<Person> findAll();
	
	Person save(Person person);
	
	void delete(Person person);
}
