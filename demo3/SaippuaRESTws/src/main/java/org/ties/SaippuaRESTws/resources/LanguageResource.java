package org.ties.SaippuaRESTws.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ties.SaippuaRESTws.models.Language;
import org.ties.SaippuaRESTws.services.LangService;

@Path("languages")
public class LanguageResource {
	LangService langService = new LangService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInstructions(@PathParam("langId") long id) {
        return "{\"queries\":\"getLang, getLang/0\"}";
    }
	
	@GET
	@Path("/first")
    @Produces(MediaType.APPLICATION_JSON)
    public Language getFirstLang() {
        return langService.getFirstLang();
    }
	
	@GET
	@Path("/{langId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Language getTask(@PathParam("langId") long id) {
        return langService.getLangById(id);
    }
	
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Language> getAllLangs() {
        return langService.getAllLangs();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Language addLang(Language lang) {
		// ei toimi vielä
		return langService.addLang(lang);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Language updateLang(Language lang) {
		// ei toimi vielä
		return langService.updateLang(lang);
	}
	
}
