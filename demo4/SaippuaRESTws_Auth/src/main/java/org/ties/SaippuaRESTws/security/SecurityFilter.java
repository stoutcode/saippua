package org.ties.SaippuaRESTws.security;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	SecurityFunctions secFuncs = new SecurityFunctions();
	// private static final String SECURED_URL_PREFIX = "languages";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if ( requestContext.getUriInfo().getPath().contains("users") ) {
			
			secFuncs.handleAuth(requestContext, Arrays.asList("admin"));
			
		}

		// Let all GET requests past the filter.
		if (requestContext.getMethod().equals("GET")) {
			return;
		}
		
		if ( requestContext.getUriInfo().getPath().contains("snippets") ) {
			
			secFuncs.handleAuth(requestContext, Arrays.asList("worker", "manager", "admin"));
			
		}
		
		if ( (requestContext.getUriInfo().getPath().contains("languages") || requestContext.getUriInfo().getPath().contains("tasks"))
				&& ( requestContext.getMethod().equals("DELETE") || requestContext.getMethod().equals("POST") || requestContext.getMethod().equals("PUT") ) ) {
			
			secFuncs.handleAuth(requestContext, Arrays.asList("manager", "admin"));
			
		}
		
	}
}
