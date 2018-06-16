package com.javasampleapproach.paractiviti.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.paractiviti.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByName(String name);

}
