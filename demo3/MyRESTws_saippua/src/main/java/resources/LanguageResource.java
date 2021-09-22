package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import models.Language;
import services.languageService;

@Path("/languages")
public class LanguageResource {

	@GET
	@Produces({"application/json"})
	public String getLanguages(){
		try {
			return languageService.getAllLanguages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("error");
		}
	}
	
	// @GET
	// @Path("/{LanguageID}")
	// @Produces("application/json")
	// public Language getLanguage(@PathParam("languageId") long id){
	// Language language = languageService.getLanguage(id);
	// return language;
	// }
    // 
	// @POST
	// @Consumes("application/json")
	// public Language addLanguage (Language language){
	// 	return languageService.addLanguage(language);
	// }

}

