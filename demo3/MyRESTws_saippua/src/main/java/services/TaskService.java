package services;

import java.util.ArrayList;
import java.util.List;

import models.Task;

public class TaskService {
	
	private List<Task> tasks = new ArrayList<Task>();
	private int id = 0;
	
	public TaskService() {
		// default empty list
	}
	
	public Task addTask(Task task) {
		tasks.add(task);
		return task;
	}
	
	public void removeTask(int id) {
		this.tasks.removeIf(task -> task.getId() == id);
	}
	
	public Task getTask(long id) {
		Task found = null;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				found = task;
				break;
			}
		}
		
		return found;
	}
	
	public List<Task> getTasks() {
		return this.tasks;
	}
	
	public Task updateTask(long id, Task updatedTask) {
		
		Task returnTask = null;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				task.setLanguage(updatedTask.getLanguage());
				task.setDescription(updatedTask.getDescription());
				task.setStatus(updatedTask.getStatus());
				break;
			}
		}
		
		return returnTask;
	}
	
	public Boolean changeTaskLanguage(int id, String language) {
		
		Boolean success = false;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				task.setLanguage(language);
				break;
			}
		}
		
		return success;
		
	}
	
	public Boolean changeTaskDescription(int id, String description) {
		
		Boolean success = false;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				task.setDescription(description);
				break;
			}
		}
		
		return success;
		
	}
	
	public Boolean changeTaskStatus(int id, String status) {
		
		Boolean success = false;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				task.setStatus(status);
				break;
			}
		}
		
		return success;
		
	}

}
