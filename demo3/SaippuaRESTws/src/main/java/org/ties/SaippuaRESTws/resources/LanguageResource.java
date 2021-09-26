package org.ties.SaippuaRESTws.resources;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ties.SaippuaRESTws.models.Language;
import org.ties.SaippuaRESTws.models.Snippet;
import org.ties.SaippuaRESTws.services.LangService;

@Path("languages")
public class LanguageResource {
	LangService langService = new LangService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getInstructions(@PathParam("langId") int id) {
        return langService.getInstructions();
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
    public Map<Object, Object> getTask(@PathParam("langId") int id) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language returnLang = langService.getLangById(id);
		
		if (returnLang == null ){
			reply.put("Languages", "no language with this index");
		} else {
			reply.put("Languages", returnLang);
		}

		return reply;
    }
	

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/name")
    public Map<Object, Object> getLangByName(@QueryParam("name") String name) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Language> returnLang = langService.getLangsByNameList(name);
		
		if (returnLang == null || returnLang.size() < 1 ) {
			reply.put("Languages", "none");
		} else {
			reply.put("Languages", returnLang);
		}

		return reply;
    }

	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<Object, Object> getAllLangs() {
		Map<Object, Object> reply = new LinkedHashMap<>();
		List<Language> returnLang = langService.getAllLangs();;
		
		if (returnLang == null || returnLang.size() < 1) {
			reply.put("Languages", "none");
		} else {
			reply.put("Languages", returnLang);
		}
		
		return reply;    
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<Object, Object> addLang(Language lang) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language returnLang = langService.addLang(lang);
		
		if (returnLang == null) {
			reply.put("Language", "none");
		} else {
			reply.put("Languages", returnLang);
		}

		return reply;
	}
	

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> updateLang(@PathParam("id") int id, Language lang) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language returnLang = langService.updateLang(id, lang);

		if (returnLang == null) {
			reply.put("Languages", "Could not update the Language. Language doesnt exist or something else went wrong.");
		} else {
			reply.put("Languages", returnLang);
		}

		return reply;
    }
	
	@DELETE
	@Path("/{id}")
	public Map<Object, Object> deleteLanguage (@PathParam("id") int id){
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language language = langService.removeLanguage (id);
		
		if (language == null) {
			reply.put("Removed", "Could not remove Language with given ID");
		} else {
			reply.put("Removed", language);
		}
		return reply;
	}
	
	@POST
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> addSnippet(@PathParam("langId") int id, Snippet snippet) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language language = langService.addSnippet(snippet, id);
		
		if (language == null) {
			reply.put("Snippet", "Could not add snippet. Something went wrong");
		} else {
			reply.put("Snippet", language);
		}

		return reply;
		
    }
	
	@PUT
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> updateSnippet(@PathParam("langId") int id, @QueryParam("id") int snipID, Snippet snippet) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		
		Language language = langService.updateSnippet(snippet, snipID, id);

		if (language == null) {
			reply.put("Snippet", "Could not add snippet. Something went wrong");
		} else {
			reply.put("Snippet", language);
		}

		return reply;	
    }
	
	@DELETE
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> deleteSnippet(@PathParam("langId") int id, @QueryParam("id") int snipID) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		
		Language language = langService.deleteSnippet(snipID, id);

		if (language == null) {
			reply.put("Snippet", "Could not delete snippet. Something went wrong");
		} else {
			reply.put("Snippet", language);
		}

		return reply;
		
    }
	
	
}