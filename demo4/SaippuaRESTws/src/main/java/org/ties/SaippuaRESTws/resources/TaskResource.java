package org.ties.SaippuaRESTws.resources;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.ties.SaippuaRESTws.exceptions.*;
import org.ties.SaippuaRESTws.exceptions.NoSuchFieldException;
import org.ties.SaippuaRESTws.models.Task;
import org.ties.SaippuaRESTws.models.TaskTeam;
import org.ties.SaippuaRESTws.services.TaskService;

@Path("tasks")
public class TaskResource {
	TaskService taskService = new TaskService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstructions(@Context UriInfo uriInfo) {
		Map<Object, Object> reply = taskService.getInstructions();
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/id")
    public Response getTaskById(@QueryParam("id") int id, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.getTaskById(id);
		
		if (returnTask == null) {
			throw new NoSuchFieldException();
		} else {
			reply.put("Task", returnTask);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
		
    }
	
	@GET
	@Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.getTaskById(id);
		
		if (returnTask == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("Task", returnTask);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/language")
    public Response getTaskById(@QueryParam("language") String language, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Task> returnTasks = taskService.getTasksByLanguage(language);
		
		if (returnTasks == null) {
			throw new NoSuchFieldException();
		} else {
			reply.put("Tasks", returnTasks);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
    }
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTasks(@Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Task> returnTasks = taskService.getTasks();
		
		if (returnTasks == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("Tasks", returnTasks);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(Task task, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.addTask(task);
		
		if (returnTask == null) {
			throw new CreateException();
		} else {
			
			addTaskLinks(uriInfo, returnTask);
			reply.put("Task", returnTask);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTask(Task task, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.updateTask(task);
		
		if (returnTask == null) {
			throw new UpdateException();
		} else {
			
			addTaskLinks(uriInfo, returnTask);
			reply.put("Task", returnTask);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteTask(@PathParam("id") int id, @Context UriInfo uriInfo){
		Map<Object, Object> reply = new LinkedHashMap<>();
		Task returnTask = taskService.removeTask(id);

		if (returnTask == null) {
			throw new DeleteException("Could not remove Task with given ID");
		} else {
			reply.put("Removed", returnTask);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
	}
	
	@GET
	@Path("/{taskId}/team")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskTeam(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.getTaskTeamById(id);
		
		if (returnTeam == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("TaskTeam", returnTeam);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
    }
	
	@POST
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.addTaskTeam(id, taskTeam);
		
		if (returnTeam == null) {
			throw new CreateException("Could not create the team. Team for id already exists or something else went wrong.");
		} else {
			
			addTaskTeamLinks(uriInfo, returnTeam);
			reply.put("TaskTeam", returnTeam);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
    }
	
	@PUT
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.updateTaskTeam(id, taskTeam);

		if (returnTeam == null) {
			throw new UpdateException("Could not update the team. Team doesnt exist or something else went wrong.");
		} else {
			addTaskTeamLinks(uriInfo, returnTeam);
			reply.put("TaskTeam", returnTeam);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
    }
	
	@DELETE
	@Path("/{taskId}/team")
	public Response deleteTeam(@PathParam("taskId") int id, @Context UriInfo uriInfo){
		Map<Object, Object> reply = new LinkedHashMap<>();
		TaskTeam returnTeam = taskService.removeTeam(id);
		
		if (returnTeam == null) {
			throw new DeleteException("Could not remove the team with given ID");
		} else {
			reply.put("Removed", returnTeam);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
	}
	
	private void addTaskLinks(@Context UriInfo uriInfo, Task returnTask) {
		String uri1 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnTask.getId())).build().toString();
		returnTask.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnTask.getId())).path("/team").build().toString();
		returnTask.addLink(uri2, "team");
		String uri3 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path("/all").build().toString();
		returnTask.addLink(uri3, "all tasks");
		String uri4 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path("/language?language=" + returnTask.getLanguage()).build().toString();
		returnTask.addLink(uri4, "task by language");
		String uri5 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path("/id?id=" + Integer.toString(returnTask.getId())).build().toString();
		returnTask.addLink(uri5, "task by id");
		
	}
	
	private void addTaskTeamLinks(@Context UriInfo uriInfo, TaskTeam returnTeam) {
		String uri1 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnTeam.getId())).path("/team").build().toString();
		returnTeam.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnTeam.getId())).build().toString();
		returnTeam.addLink(uri2, "task");
		
	}
	
}
