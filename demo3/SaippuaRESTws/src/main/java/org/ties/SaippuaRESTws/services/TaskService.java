package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Link;
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
		
		task.setId(nextId());
		
		String language = task.getLanguage();
		String status = task.getStatus();
		
		if (language != null) {
			task.setLanguage(language.trim());
		}
		
		if (status!= null) {
			task.setStatus(status.trim());
		}
		
		addTaskLinks(task);
		
		tasks.add(task);
		
		return task;
	}
	
	public Task updateTask(Task updatedTask) {
		Task returnTask = null;
		
		int id = updatedTask.getId();
		
		try {
			for (Task task : tasks) {
				if(task.getId() == id) {
					updatedTask = addTaskLinks(updatedTask);
					tasks.set(tasks.indexOf(task), updatedTask);
					returnTask = updatedTask;
				}
			}
		} catch (Exception e) {
			return returnTask;
		}
		
		
		return returnTask;
	}
	
	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		
		Link instructions = new Link("/", "instructions");
		links.add(instructions);
		
		Link id = new Link("/tasks/id", "search by id as parameter, i.e. /id?1");
		links.add(id);
		
		Link id2 = new Link("/tasks/1", "search by id number");
		links.add(id2);
		
		Link language = new Link("/tasks/language", "search by lanugage as parameter, i.e /language?java");
		links.add(language);
		
		Link all = new Link("/tasks/all", "get all tasks");
		links.add(all);
		
		Link post = new Link("/tasks/", "POST new task as json");
		links.add(post);
		
		Link put = new Link("/tasks/", "PUT changes to existing task with same id");
		links.add(put);
		
		Link teamGet = new Link("/tasks/{1}/team", "GET task team");
		links.add(teamGet);
		
		Link teamPost = new Link("/tasks/{1}team/", "POST new team as json");
		links.add(teamPost);
		
		Link teamPut = new Link("/tasks/{1}team/", "PUT changes to team as json");
		links.add(teamPut);
		
		return links;
	}

	public Map<Object, Object> getInstructions() {
		
		Map<Object, Object> instructions = new LinkedHashMap<>();
		
		instructions.put("Info", "This url is for Taskservice. See list of links.");
		instructions.put("Links", getLinks());
		
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
			
			taskTeam = addTaskTeamLinks(taskTeam);
			
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
					taskTeam = addTaskTeamLinks(taskTeam);
					taskTeams.set(taskTeams.indexOf(team), taskTeam);
					returnTeam = taskTeam;
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		
		
		return returnTeam;
	}
	
	private Task addTaskLinks(Task task) {

		task.addLink("/", "instructions");
		task.addLink("/tasks/id", "search by id as parameter, i.e. /id?1");
		task.addLink("/tasks/1", "search by id number");
		task.addLink("/tasks/language", "search by lanugage as parameter, i.e /language?java");
		task.addLink("/tasks/all", "get all tasks");
		task.addLink("/tasks/", "POST new task as json");
		task.addLink("/tasks/", "PUT changes to existing task with same id");
		task.addLink("/tasks/{1}/team", "GET task team");
		
		return task;

	}
	
	private TaskTeam addTaskTeamLinks(TaskTeam team) {
		team.addLink("/tasks/{1}/team", "GET task team");
		team.addLink("/tasks/{1}/team/", "POST new team as json");
		team.addLink("/tasks/{1}/team/", "PUT changes to team as json");
		
		return team;
		
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
