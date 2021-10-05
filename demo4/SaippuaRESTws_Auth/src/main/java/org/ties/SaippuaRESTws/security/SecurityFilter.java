package org.ties.SaippuaRESTws.security;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	SecurityFunctions secFuncs = new SecurityFunctions();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		if ( requestContext.getUriInfo().getPath().contains("users") ) {
			secFuncs.handleAuth(requestContext, Arrays.asList("admin"));
			return;
		}
		
		if (requestContext.getMethod().equals("GET")) {
			return;
		}

		if ( requestContext.getUriInfo().getPath().contains("snippet") ) {
			secFuncs.handleAuth(requestContext, Arrays.asList("worker", "manager", "admin"));
			return;
		}
		
		if ( (requestContext.getUriInfo().getPath().contains("languages") || requestContext.getUriInfo().getPath().contains("tasks"))
				&& ( requestContext.getMethod().equals("DELETE") || requestContext.getMethod().equals("POST") || requestContext.getMethod().equals("PUT") ) ) {
			secFuncs.handleAuth(requestContext, Arrays.asList("manager", "admin"));
			return;
		}
		
	}
}
