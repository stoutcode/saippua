package org.ties.SaippuaRESTws.resources;

import java.net.URI;
import java.util.List;

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
	public Response getTasks(@QueryParam("language") String language, @Context UriInfo uriInfo) {
		List<Task> returnTasks = null;
		if (language == null)
			returnTasks = taskService.getTasks();
		else {
			returnTasks = taskService.getTasksByLanguage(language);
			if (returnTasks == null)
				throw new NoSuchFieldException();
		}
		
		if (returnTasks == null)
			throw new DataNotFoundException();
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnTasks).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTask(Task task, @Context UriInfo uriInfo) {
		Task returnTask = taskService.addTask(task);
		
		if (returnTask == null) {
			throw new CreateException();
		}

		addTaskLinks(uriInfo, returnTask);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnTask).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTask(Task task, @Context UriInfo uriInfo) {
		Task returnTask = taskService.updateTask(task);
		
		if (returnTask == null)
			throw new UpdateException();

		addTaskLinks(uriInfo, returnTask);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnTask).build();
	}
	
	@GET
	@Path("/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
		Task returnTask = taskService.getTaskById(id);
		
		if (returnTask == null) {
			throw new DataNotFoundException();
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnTask).build();
    }
	
	@DELETE
	@Path("/{taskId}")
	public Response deleteTask(@PathParam("taskId") int id, @Context UriInfo uriInfo){
		Task returnTask = taskService.removeTask(id);

		if (returnTask == null) {
			throw new DeleteException("Could not remove Task with given ID");
		}

		return Response.ok().build();
	}

	@GET
	@Path("/{taskId}/team")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTaskTeam(@PathParam("taskId") int id, @Context UriInfo uriInfo) {
		TaskTeam returnTeam = taskService.getTaskTeamById(id);
		
		if (returnTeam == null) {
			throw new DataNotFoundException();
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnTeam).build();
    }

	@POST
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response getTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam, @Context UriInfo uriInfo) {
		TaskTeam returnTeam = taskService.addTaskTeam(id, taskTeam);
		
		if (returnTeam == null) {
			throw new CreateException("Could not create the team. Team for id already exists or something else went wrong.");
		}
	
		addTaskTeamLinks(uriInfo, returnTeam);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnTeam).build();
    }
	
	@PUT
	@Path("/{taskId}/team")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateTaskTeam(@PathParam("taskId") int id, TaskTeam taskTeam, @Context UriInfo uriInfo) {
		TaskTeam returnTeam = taskService.updateTaskTeam(id, taskTeam);

		if (returnTeam == null) {
			throw new UpdateException("Could not update the team. Team doesnt exist or something else went wrong.");
		}

		addTaskTeamLinks(uriInfo, returnTeam);

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnTeam).build();
    }
	
	@DELETE
	@Path("/{taskId}/team")
	public Response deleteTeam(@PathParam("taskId") int id, @Context UriInfo uriInfo){
		TaskTeam deletedTeam = taskService.removeTeam(id);
		
		if (deletedTeam == null) {
			throw new DeleteException("Could not remove the team with given ID");
		}

		return Response.ok().build();
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
