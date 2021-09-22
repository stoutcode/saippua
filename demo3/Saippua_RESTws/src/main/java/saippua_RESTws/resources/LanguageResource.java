package saippua_RESTws.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import saippua_RESTws.models.Language;

@Path("/languages")
public class LanguageResource {

	@GET
	@Produces({"application/json"})
	public String getLanguages(){
		return "asd"; //languageService.getAllLanguages();;
	}
	
	@GET
	@Path("/{LanguageID}")
	@Produces("application/json")
	public Language getLanguage(@PathParam("languageId") long id) {
	//Language language = languageService.getLanguage(id);
	return publication;
	}

	@POST
	@Consumes("application/json")
	public Language addLanguage (Language language) {
		// return languageService.addLanguage(language);
	}

}

