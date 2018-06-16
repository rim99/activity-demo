package com.javasampleapproach.paractiviti.controller;

import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.paractiviti.service.MyService;

@RestController
public class MyController {
	@Autowired
	private MyService myService;

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation) {
		myService.startProcess(startProcessRepresentation.getAssignees());
	}

	@RequestMapping(value = "/tasks/{assignee}")
	public String getTasks(@PathVariable("assignee") String assignee) {
		List<Task> tasks = myService.getTasks(assignee);
		return tasks.toString();
	}

	@RequestMapping(value = "/completetask")
	public String completeTask(@RequestParam("id") String id) {
		myService.completeTask(id);
		return "Task with id " + id + " has been completed!";
	}

	static class StartProcessRepresentation {

		private String[] assignees;

		public String[] getAssignees() {
			return assignees;
		}

	}

}
