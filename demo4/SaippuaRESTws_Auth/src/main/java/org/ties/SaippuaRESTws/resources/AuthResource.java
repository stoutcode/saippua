package org.ties.SaippuaRESTws.resources;


import java.util.List;
import java.net.URI;



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
import org.ties.SaippuaRESTws.models.Language;
import org.ties.SaippuaRESTws.models.Snippet;
import org.ties.SaippuaRESTws.services.LangService;

@Path("authorization")
public class AuthResource {
	
	@GET
    public Response Null() {
		
		return Response.ok().build();
    }
	
}
