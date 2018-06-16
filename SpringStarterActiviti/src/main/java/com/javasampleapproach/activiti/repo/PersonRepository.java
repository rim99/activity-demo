package com.javasampleapproach.activiti.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.activiti.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByName(String name);

}
