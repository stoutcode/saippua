package org.ties.SaippuaRESTws.resources;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import org.ties.SaippuaRESTws.security.SecurityFunctions;
import org.ties.SaippuaRESTws.security.TokenUtility;

@Path("authorize")
public class AuthResource {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	
	@GET
    public Response login(ContainerRequestContext requestContext) {
		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		String[] credentials = SecurityFunctions.decode(authHeader);
		String username = credentials[0];
		String password = credentials[1];
	
		List<Object> jwt = new ArrayList<Object>();

        System.out.println("login succesful, sending jwt back" + jwt);
        jwt.add(TokenUtility.buildJWT(username, password));
        return Response.ok().header("Authorization", "Bearer " + jwt.toString()).build();
    }
	

}
