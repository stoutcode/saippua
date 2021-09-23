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

			e.printStackTrace();
			return("error");
		}
	}
	
	 @GET
	 @Path("/{LanguageID}")
	 @Produces("application/json")
	 public String getLanguage(@PathParam("languageId") long id){
	 String language;
	try {
		language = languageService.getLanguage(id);
		} catch (Exception e) {
		e.printStackTrace();
		return("error");
		}
	 return language;
	 }
     
	 @POST
	 @Consumes("application/json")
	 public String addLanguage (Language language){
	 	return languageService.addLanguage(language);
	 }
	 
//	 @PUT
//	 @Path("/{publicationId}")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Publication updatePublication(@PathParam("publicationId") long id, Publication publication){
//		 publication.setId(id);
//		 return publicationService.updatePublication(publication);
//	 }
//	 
//	 @DELETE
//	 @Path("/{publicationId}")
//	 public void deletePublication (@PathParam("publicationId") long id){
//	 publicationService.removePublication (id);
//	 }


}

