package org.ties.SaippuaRESTws.security;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.codec.binary.Base64;
import org.ties.SaippuaRESTws.models.ErrorMessage;
import org.ties.SaippuaRESTws.models.User;
import org.ties.SaippuaRESTws.services.UserService;

public class SecurityFunctions {
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final User admin = new User("super", "user", "super", "super@user.com", "user", "admin");
	private UserService userService = new UserService();
	
	public boolean checkAuthentication(List<String> authHeader, List<String> roles) {
		if (authHeader == null || authHeader.size() < 1) {
			return false;
		}
		
		String authToken = authHeader.get(0);
		
		authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
		
		String decodedString = new String(Base64.decodeBase64(authToken));
	
		StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
		
		String username = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		System.out.println(username);
		System.out.println(password);
		
		return checkCredentialsToRoles(username, password, roles);

	}

	private boolean checkCredentialsToRoles(String username, String password, List<String> roles) {
		List<User> users = userService.getUsers();
		
		if( username.equals(admin.getUsername()) && password.equals(admin.getPassword()) ) {
			return true;
		}

		for (User user : users) {
			if ( user.getUsername().equals(username) && user.getPassword().equals(password) && roles.contains(user.getRole()) ) {
				return true;
			}
		}

		return false;
	}

	public void handleAuth(ContainerRequestContext requestContext, List<String> roles) {		
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);

		if (checkAuthentication(authHeader, roles) ) {
			return;
		}

		ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401,
		"http://myDocs.org");

		Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
		.entity(errorMessage)
		.build();

		requestContext.abortWith(unauthorizedStatus);
		
	}
 
}
