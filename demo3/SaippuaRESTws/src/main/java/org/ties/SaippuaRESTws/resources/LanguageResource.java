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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.ties.SaippuaRESTws.exceptions.*;
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
			throw new DataNotFoundException("No language with this index");
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
			throw new DataNotFoundException();
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
			throw new DataNotFoundException();
		} else {
			reply.put("Languages", returnLang);
		}
		
		return reply;    
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<Object, Object> addLang(Language lang, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language returnLang = langService.addLang(lang);
		
		if (returnLang == null) {
			throw new CreateException("Error while creating new language.");
		} else {
			addLanguageLinks(uriInfo, returnLang);
			reply.put("Languages", returnLang);
		}

		return reply;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> updateLang(@PathParam("id") int id, Language lang, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language returnLang = langService.updateLang(id, lang);

		if (returnLang == null) {
			throw new UpdateException("Could not update the Language. Language doesnt exist or something else went wrong.");
		} else {
			addLanguageLinks(uriInfo, returnLang);
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
			throw new DeleteException("Could not remove Language with given ID");
		} else {
			reply.put("Removed", language);
		}
		return reply;
	}
	
	@POST
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> addSnippet(@PathParam("langId") int id, Snippet snippet, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		Language language = langService.addSnippet(snippet, id);
		
		if (language == null) {
			throw new CreateException("Could not add snippet. Something went wrong");
		} else {
			addLanguageLinks(uriInfo, language);
			reply.put("Snippet", language);
		}

		return reply;
		
    }
	
	@PUT
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Map<Object, Object> updateSnippet(@PathParam("langId") int id, @QueryParam("id") int snipID, Snippet snippet, @Context UriInfo uriInfo) {
		Map<Object, Object> reply = new LinkedHashMap<>();
		
		Language language = langService.updateSnippet(snippet, snipID, id);

		if (language == null) {
			throw new UpdateException("Could not update snippet. Something went wrong");
		} else {
			addLanguageLinks(uriInfo, language);
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
			throw new DeleteException("Could not delete snippet. Something went wrong");
		} else {
			reply.put("Snippet", language);
		}

		return reply;
		
    }
	
	private void addLanguageLinks(UriInfo uriInfo, Language returnLang) {
		
		String uri1 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnLang.getId())).build().toString();
		returnLang.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnLang.getId())).path("/snippet").build().toString();
		returnLang.addLink(uri2, "post snippets");
		String uri3 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path(Integer.toString(returnLang.getId())).path("/snippet?id=id").build().toString();
		returnLang.addLink(uri3, "put changes to snippet");
		String uri4 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path("/all").build().toString();
		returnLang.addLink(uri4, "all languages");
		String uri5 = uriInfo.getBaseUriBuilder().path(TaskResource.class).path("/name?name=" + returnLang.getName()).build().toString();
		returnLang.addLink(uri5, "language by name");
		
	}
	
	
}