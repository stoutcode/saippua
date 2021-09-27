package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.exceptions.CreateException;
import org.ties.SaippuaRESTws.models.Task;
import org.ties.SaippuaRESTws.models.TaskTeam;


public class TaskService {
	private static List<Task> tasks = new ArrayList<>();
	private static List<TaskTeam> taskTeams = new ArrayList<>();
	private static int id = 0;

	private int nextId() {
		return ++id;
	}

	public TaskService() {

	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public List<TaskTeam> getTaskTeams() {
		return taskTeams;
	}

	public Task getTaskById(int id) {
		Task returnTask = null;
		
		for (Task task : tasks) {
			if(task.getId() == id) {
				returnTask = task;
			}
		}
		
		return returnTask;
	}

	public List<Task> getTasksByLanguage(String language) {
		List<Task> returnTasks = new ArrayList<>();
		
		for (Task task : tasks) {
			if(task.getLanguage().trim().toLowerCase().equals(language.trim().toLowerCase())) {
				returnTasks.add(task);
			}
		}
		
		return returnTasks;
	}
	
	public Task addTask(Task task) {
		for (Task existingTask : this.getTasks()) {
			if (task.getDescription().equals(existingTask.getDescription()))
				throw new CreateException("Task with the same description already exists.");
		}
		
		task.setId(nextId());
		
		String language = task.getLanguage();
		String status = task.getStatus();
		
		if (language != null) {
			task.setLanguage(language.trim());
		}
		
		if (status!= null) {
			task.setStatus(status.trim());
		}
		
		tasks.add(task);
		
		return task;
	}
	
	public Task updateTask(Task updatedTask) {
		Task returnTask = null;
		
		int id = updatedTask.getId();
		
		try {
			for (Task task : tasks) {
				if(task.getId() == id) {
					tasks.set(tasks.indexOf(task), updatedTask);
					returnTask = updatedTask;
				}
			}
		} catch (Exception e) {
			return returnTask;
		}
		
		
		return returnTask;
	}

	public Map<Object, Object> getInstructions() {
		
		Map<Object, Object> instructions = new LinkedHashMap<>();
		
		instructions.put("Info", "This url is for Taskservice.");
		
		return instructions;
	}

	public TaskTeam getTaskTeamById(int id) {
		
		TaskTeam returnTeam = null;
		
		try {
			for (TaskTeam team : taskTeams) {
				if(team.getId() == id) {
					returnTeam = team;
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		
		return returnTeam;
	}

	public TaskTeam addTaskTeam(int id, TaskTeam taskTeam) {
		TaskTeam returnTeam = null;
		
		Boolean existingTeam = false;
		Boolean existingTask = false;
		
		try {
			for (Task task : tasks) {
				if(task.getId() == id) {
					
					existingTask = true;
					
					try {
						for (TaskTeam team : taskTeams) {
							if(team.getId() == id) {
								existingTeam = true;
								break;
							}
						}
					} catch (Exception e) {
						return returnTeam;
					}
					
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		
		if (existingTask && !existingTeam) {
			String members = taskTeam.getTeam();
			
			// replace id with task id to be sure it's same
			taskTeam.setId(id);
			
			if (members != null) {
				taskTeam.setTeam(taskTeam.getTeam().trim());
			}
			
			taskTeams.add(taskTeam);
			returnTeam = taskTeam;
		}
		
		return returnTeam;
	}

	public TaskTeam updateTaskTeam(int id, TaskTeam taskTeam) {
		TaskTeam returnTeam = null;
		
		try {
			for (TaskTeam team: taskTeams) {
				if(team.getId() == id) {
					// replace id with task id to be sure it's same
					taskTeam.setId(id);
					taskTeams.set(taskTeams.indexOf(team), taskTeam);
					returnTeam = taskTeam;
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		
		
		return returnTeam;
	}

	public Task removeTask(int id) {
		Task returnTask = null;
		try {
			for (Task task : tasks) {
				if(task.getId() == id) {
					returnTask = task;
					tasks.remove(task);
					
					for (TaskTeam team : taskTeams) {
						if(team.getId() == id) {
							taskTeams.remove(team);
							break;
						}
					}
					
					break;
				}
			}
		} catch (Exception e) {
			return returnTask;
		}
		return returnTask;
	}

	public TaskTeam removeTeam(int id) {
		TaskTeam returnTeam = null;
		try {
			for (TaskTeam team : taskTeams) {
				if(team.getId() == id) {
					returnTeam = team;
					taskTeams.remove(team);
					break;
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		return returnTeam;
	}
	
}
