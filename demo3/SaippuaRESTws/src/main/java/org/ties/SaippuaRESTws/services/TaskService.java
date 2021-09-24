package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Link;
import org.ties.SaippuaRESTws.models.Task;
import org.ties.SaippuaRESTws.models.TaskTeam;


public class TaskService {
	private List<Task> tasks;
	private List<TaskTeam> taskTeams;
	private int id = 0;
	
	private int nextId() {
		return ++this.id;
	}
	
	public TaskService() {
		tasks = new ArrayList<>();
		taskTeams = new ArrayList<>();
		Task test = new Task(0, "Java", "requested state of the art feature to make SISU work", "blocked");
		tasks.add(test);
		
		TaskTeam team1 = new TaskTeam(0, "SuperGuy, SpeedSteve");
		taskTeams.add(team1);
	}

	public List<Task> getAllTasks() {
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
		
		tasks.add(task);
		
		return task;
	}
	
	public Task updateTask(Task updatedTask) {
		Task returnTask = null;
		
		int id = updatedTask.getId();
		
		try {
			for (Task task : tasks) {
				if(task.getId() == id) {
					task = updatedTask;
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
		
		Link id2 = new Link("/tasks/0", "search by id number");
		links.add(id2);
		
		Link language = new Link("/tasks/language", "search by lanugage as parameter, i.e /language?java");
		links.add(language);
		
		Link all = new Link("/tasks/all", "get all tasks");
		links.add(all);
		
		Link post = new Link("/tasks/", "POST new link as json");
		links.add(post);
		
		Link put = new Link("/tasks/", "PUT changes to existing link with same id");
		links.add(put);
		
		Link teamPost = new Link("/teams/", "POST new team as json");
		links.add(teamPost);
		
		Link teamGet = new Link("/task/{0}/team", "GET task team");
		links.add(teamGet);
		
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

	public TaskTeam addTaskTeam(TaskTeam taskTeam) {		
		TaskTeam returnTeam = null;
		int id = taskTeam.getId();
		
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
			
			String members = taskTeam.getMembers();
			
			if (members != null) {
				taskTeam.replaceMembers(taskTeam.getMembers().trim());
			}
			
			taskTeams.add(taskTeam);
			returnTeam = taskTeam;
		}
		
		return returnTeam;
	}

	public TaskTeam updateTaskTeam(TaskTeam taskTeam) {
		TaskTeam returnTeam = null;
		
		int id = taskTeam.getId();
		
		try {
			for (TaskTeam team: taskTeams) {
				if(team.getId() == id) {
					team = taskTeam;
					returnTeam = taskTeam;
				}
			}
		} catch (Exception e) {
			return returnTeam;
		}
		
		
		return returnTeam;
	}
	
}
