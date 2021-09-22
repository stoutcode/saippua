package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Task;
import services.TaskService;

@Path("/tasks")
public class TaskResource {
	TaskService taskService = new TaskService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasks() {
		return taskService.getTasks();
	}
	
	@GET
	@Path("/{taskId: ^\\d+$}")
	@Produces(MediaType.APPLICATION_JSON)
	public Task getTask(@PathParam("taskId") long id) {
		Task task = taskService.getTask(id);
		return task;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Task addTask(Task task) {
		return taskService.addTask(task);
	}
	
	@PUT
	@Path("/{taskId: ^\\d+$}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Task updateTask(@PathParam("taskId") long id, Task task) {
		return this.taskService.updateTask(id, task);
	}

}
