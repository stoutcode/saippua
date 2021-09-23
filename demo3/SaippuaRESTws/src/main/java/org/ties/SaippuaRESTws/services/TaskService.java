package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Task;


public class TaskService {
	private List<Task> tasks = new ArrayList<>();
	private int id = 0;
	
	private int nextId() {
		return ++this.id;
	}
	
	public TaskService() {
		Task test = new Task(0, "Java", "requested state of the art feature to make SISU work", "blocked");
		this.tasks.add(test);
	}
	
	public Task getFirstTask() {
		return this.tasks.get(0);
	}
	
	public List<Task> getAllTasks() {
		return this.tasks;
	}

	public Task getTaskById(long id) {
		Task returnTask = null;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				returnTask = task;
			}
		}
		
		return returnTask;
	}
	
	public Task addTask(Task task) {
		
		Task returnTask = null;
		
		try {
			String language = task.getLanguage();
			String description = task.getDescription();
			String status = task.getStatus();
			
			int id = nextId();
			Task newTask = new Task(id, language, description, status);
			
			this.tasks.add(newTask);
			
			returnTask = newTask;
			
		} catch (Exception e) {
			return returnTask;
			
		}
		
		return returnTask;
	}
	
	public Task updateTask(Task updatedTask) {
		Task returnTask = null;
		
		try {
			for (Task task : tasks) {
				if(task.getId() == updatedTask.getId()) {
					task = updatedTask;
					returnTask = updatedTask;
				}
			}
		} catch (Exception e) {
			return returnTask;
		}
		
		
		return returnTask;
	}
}
