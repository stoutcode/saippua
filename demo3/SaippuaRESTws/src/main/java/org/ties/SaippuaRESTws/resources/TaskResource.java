package org.ties.SaippuaRESTws.resources;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ties.SaippuaRESTws.models.Task;
import org.ties.SaippuaRESTws.models.TaskTeam;
import org.ties.SaippuaRESTws.services.TaskService;

@Path("tasks")
public class TaskResource {
	private TaskService taskService = new TaskService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getInstructions() {
		return taskService.getInstructions();
		
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
    public Map<Object, Object> getTaskById(@QueryParam("taskId") int id) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.getTaskById(id);
		
		if (returnTask == null) {
			reply.put("Task", "none");
		} else {
			reply.put("Task", returnTask);
		}

		return reply;
		
    }
	
	@GET
	@Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getTask(@PathParam("taskId") int id) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.getTaskById(id);
		
		if (returnTask == null) {
			reply.put("Task", "none");
		} else {
			reply.put("Task", returnTask);
		}

		return reply;
		
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/language")
    public Map<Object, Object> getTaskById(@QueryParam("language") String language) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Task> returnTasks = taskService.getTasksByLanguage(language);
		
		if (returnTasks == null || returnTasks.size() < 1) {
			reply.put("Tasks", "none");
		} else {
			reply.put("Tasks", returnTasks);
		}

		return reply;
		
    }
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getAllTasks() {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Task> returnTasks = taskService.getTasks();
		
		if (returnTasks == null || returnTasks.size() < 1) {
			reply.put("Tasks", "none");
		} else {
			reply.put("Tasks", returnTasks);
		}
		
		return reply;
        
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<Object, Object> addTask(Task task) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.addTask(task);
		
		if (returnTask == null) {
			reply.put("Task", "none");
		} else {
			reply.put("Task", returnTask);
		}

		return reply;
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<Object, Object> updateTask(Task task) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.updateTask(task);
		
		if (returnTask == null) {
			reply.put("Task", "none");
		} else {
			reply.put("Task", returnTask);
		}

		return reply;
		
	}
	
	@GET
	@Path("/{taskId}/team")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getTaskTeam(@PathParam("taskId") int id) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.getTaskTeamById(id);
		
		if (returnTeam == null) {
			reply.put("TaskTeam", "none");
		} else {
			reply.put("TaskTeam", returnTeam);
		}

		return reply;
		
    }
	
	@POST
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.addTaskTeam(id, taskTeam);
		
		if (returnTeam == null) {
			reply.put("TaskTeam", "Could not create the team. Team for id already exists or something else went wrong.");
		} else {
			reply.put("TaskTeam", returnTeam);
		}

		return reply;
		
    }
	
	@PUT
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> updateTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.updateTaskTeam(id, taskTeam);

		if (returnTeam == null) {
			reply.put("TaskTeam", "Could not update the team. Team doesnt exist or something else went wrong.");
		} else {
			reply.put("TaskTeam", returnTeam);
		}

		return reply;
		
    }
	
}
