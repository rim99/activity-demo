package com.javasampleapproach.paractiviti.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javasampleapproach.paractiviti.model.Person;
import com.javasampleapproach.paractiviti.repo.PersonRepository;

@Service
@Transactional
public class MyService {
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private PersonRepository personRepository;

	public void startProcess(String[] assignees) {

		Map<String, Object> variables = new HashMap<String, Object>();

		Person hardwareChecker = personRepository.findByName(assignees[0]);
		Person softwareChecker = personRepository.findByName(assignees[1]);
		Person reporter = personRepository.findByName(assignees[2]);

		variables.put("hardwareChecker", hardwareChecker);
		variables.put("softwareChecker", softwareChecker);
		variables.put("reporter", reporter);
		
		runtimeService.startProcessInstanceByKey("fixSystemProcess", variables);
	}

	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}

	public void completeTask(String taskId) {
		taskService.complete(taskId);
	}

	public void createPersons() {
		if (personRepository.findAll().size() == 0) {

			personRepository.save(new Person("John", new Date()));
			personRepository.save(new Person("David", new Date()));
			personRepository.save(new Person("Katherin", new Date()));
		}
	}
}
