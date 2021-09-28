package org.ties.SaippuaRESTws.services;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.ties.SaippuaRESTws.models.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "languages";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Let all GET requests past the filter.
		if (requestContext.getMethod().equals("GET")) return;
		if ((requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) || (requestContext.getMethod().equals("DELETE"))) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				//String decodedString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(authToken, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				System.out.println(username);
				System.out.println(password);
				if ("user".equals(username) && "password".equals(password)) { return; }
			}
			ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401,
			"http://myDocs.org");
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
			.entity(errorMessage)
			.build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
}
