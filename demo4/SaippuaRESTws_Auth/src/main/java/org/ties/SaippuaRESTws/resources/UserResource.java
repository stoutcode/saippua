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
import org.ties.SaippuaRESTws.exceptions.CreateException;
import org.ties.SaippuaRESTws.exceptions.DataNotFoundException;
import org.ties.SaippuaRESTws.exceptions.UpdateException;
import org.ties.SaippuaRESTws.models.User;
import org.ties.SaippuaRESTws.services.UserService;

@Path("users")
public class UserResource {
	UserService userService = new UserService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@Context UriInfo uriInfo) {
		List<User> returnUsers= userService.getUsers();

		if (returnUsers == null)
			throw new DataNotFoundException();

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnUsers).build();
	}

	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(@PathParam("username") String username, @Context UriInfo uriInfo) {
		User returnUser = userService.getUserByUsername(username);
		
		if (returnUser == null)
			throw new DataNotFoundException();
		
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnUser).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user, @Context UriInfo uriInfo) {
		User returnUser = userService.addUser(user);

		if (returnUser == null)
			throw new CreateException("Username already exists, or something else went wrong.");
		
		addUserLinks(uriInfo, returnUser);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnUser).build();
	}

	@PUT
	@Path("/{username}")
	public Response changeUserRole(@PathParam("username") String username, @QueryParam("role") String role, @Context UriInfo uriInfo) {
		if (role == null || !userService.isValidRole(role))
			throw new UpdateException("Bad role or user. Role must be one of: " + userService.getRoles());
		
		User returnUser = userService.setRole(username, role);
		
		if (returnUser == null)
			throw new UpdateException("Something went wrong.");
		
		addUserLinks(uriInfo, returnUser);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnUser).build();
	}

	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam("username") String username, @Context UriInfo uriInfo) {
		User returnUser = userService.deleteUser(username);
		
		if (returnUser == null)
			throw new DataNotFoundException();
		
		return Response.ok().build();
	}

	private void addUserLinks(@Context UriInfo uriInfo, User returnUser) {
		String uri1 = uriInfo.getBaseUriBuilder().path(UserResource.class).build().toString();
		returnUser.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(UserResource.class).path(returnUser.getUsername()).build().toString();
		returnUser.addLink(uri2, "user");
	}

}
