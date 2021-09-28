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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.ties.SaippuaRESTws.exceptions.CreateException;
import org.ties.SaippuaRESTws.exceptions.DataNotFoundException;
import org.ties.SaippuaRESTws.exceptions.UpdateException;
import org.ties.SaippuaRESTws.models.User;
import org.ties.SaippuaRESTws.services.UserService;

@Path("users")
public class UserResource {
	UserService userService = new UserService();
	
	@GET
	public Response getUsers(@Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<User> returnUsers= userService.getUsers();
		
		if (returnUsers == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("Users", returnUsers);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
	}
	
	@GET
	@Path("/{username}")
	public Response getUsers(@PathParam("username") String username, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		User returnUser = userService.getUserByUsername(username);
		
		if (returnUser == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("User", returnUser);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		User returnUser = userService.addUser(user);
		
		if (returnUser == null) {
			throw new CreateException();
		} else {
			
			addUserLinks(uriInfo, returnUser);
			reply.put("User", returnUser);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateTaskTeam(User user, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		User returnUser = userService.changeUser(user);

		if (returnUser == null) {
			throw new UpdateException("Could not update the user.");
		} else {
			addUserLinks(uriInfo, returnUser);
			reply.put("User", returnUser);
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(reply).build();
    }
	
	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam("username") String username, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		User returnUser = userService.deleteUser(username);
		
		if (returnUser == null) {
			throw new DataNotFoundException();
		} else {
			reply.put("User", returnUser);
		}
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(reply).build();
	}
	
	
	private void addUserLinks(@Context UriInfo uriInfo, User returnUser) {
		String uri1 = uriInfo.getBaseUriBuilder().path(UserResource.class).build().toString();
		returnUser.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(UserResource.class).path(returnUser.getUsername()).build().toString();
		returnUser.addLink(uri2, "user");
		
	}
	
}
