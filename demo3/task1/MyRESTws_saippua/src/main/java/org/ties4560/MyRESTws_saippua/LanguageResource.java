package org.ties4560.MyRESTws_saippua;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/languages")
public class LanguageResource {
@GET
//@Produces("text/plain")
@Produces({"application/json"})
// @Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	
	public String getLanguages(){
		return "{\"languages\": \"languages\"}";
	}
}

