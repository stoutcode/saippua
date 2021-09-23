package org.ties.SaippuaRESTws.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ties.SaippuaRESTws.models.Task;
import org.ties.SaippuaRESTws.services.TaskService;

@Path("tasks")
public class TaskResource {
	TaskService taskService = new TaskService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInstructions(@PathParam("taskId") long id) {
        return "{\"queries\":\"getTask, getTask/0\"}";
    }
	
	@GET
	@Path("/first")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getFirstTask() {
        return taskService.getFirstTask();
    }
	
	@GET
	@Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTask(@PathParam("taskId") long id) {
        return taskService.getTaskById(id);
    }
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Task addTask(Task task) {
		// ei toimi vielä
		return taskService.addTask(task);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Task updateTask(Task task) {
		// ei toimi vielä
		return taskService.updateTask(task);
	}
	
}
