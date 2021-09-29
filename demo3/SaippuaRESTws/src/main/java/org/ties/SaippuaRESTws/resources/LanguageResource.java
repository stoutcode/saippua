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

@Path("languages")
public class LanguageResource {
	LangService langService = new LangService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLangs(@QueryParam("name") String name, @Context UriInfo uriInfo) {
		List<Language> returnLangs = null;
		
		if (name == null) {
			returnLangs = langService.getAllLangs();
		} else {
			returnLangs = langService.getLangsByName(name);
		}
		
		if (returnLangs == null)
			throw new DataNotFoundException();

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnLangs).build();
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLang(Language lang, @Context UriInfo uriInfo) {
		Language returnLang = langService.addLang(lang);
		
		if (returnLang == null) {
			throw new CreateException("Error while creating new language.");
		}

		addLanguageLinks(uriInfo, returnLang);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnLang).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateLang(Language lang, @Context UriInfo uriInfo) {
		Language returnLang = langService.updateLang(lang);

		if (returnLang == null) {
			throw new UpdateException("Could not update the Language. Language doesnt exist or something else went wrong.");
		}

		addLanguageLinks(uriInfo, returnLang);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(returnLang).build();
    }
	
	@GET
	@Path("/{langId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLang(@PathParam("langId") int id, @Context UriInfo uriInfo) {
		Language returnLang = langService.getLangById(id);
		
		if (returnLang == null ) {
			throw new DataNotFoundException("No language with this index");
		}

		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.ok(uri).entity(returnLang).build();
    }
	
	@DELETE
	@Path("/{langId}")
	public Response deleteLanguage (@PathParam("langId") int id, @Context UriInfo uriInfo){
		Language language = langService.removeLanguage (id);
		
		if (language == null) {
			throw new DeleteException("Could not remove Language with given ID");
		}

		return Response.ok().build();
	}
	
	@POST
	@Path("/{langId}/snippet")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addSnippet(@PathParam("langId") int id, Snippet snippet, @Context UriInfo uriInfo) {
		Language language = langService.addSnippet(snippet, id);
		
		if (language == null) {
			throw new CreateException("Could not add snippet. Something went wrong");
		}
		
		addLanguageLinks(uriInfo, language);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(language).build();
		
    }
	
	@PUT
	@Path("/{langId}/snippet/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response updateSnippet(@PathParam("langId") int id, @PathParam("id") int snipID, Snippet snippet, @Context UriInfo uriInfo) {
		Language language = langService.updateSnippet(snippet, snipID, id);

		if (language == null) {
			throw new UpdateException("Could not update snippet. Something went wrong");
		}
			
		addLanguageLinks(uriInfo, language);
		URI uri = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(uri).entity(language).build();
    }
	
	@DELETE
	@Path("/{langId}/snippet/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSnippet(@PathParam("langId") int id, @PathParam("id") int snipID, @Context UriInfo uriInfo) {
		Snippet snippet = langService.deleteSnippet(snipID, id);

		if (snippet == null) {
			throw new DeleteException("Could not delete snippet. Something went wrong");
		}

		return Response.ok().build();
		
    }
	
	private void addLanguageLinks(UriInfo uriInfo, Language returnLang) {
		
		String uri1 = uriInfo.getBaseUriBuilder().path(LanguageResource.class).path(Integer.toString(returnLang.getId())).build().toString();
		returnLang.addLink(uri1, "self");
		String uri2 = uriInfo.getBaseUriBuilder().path(LanguageResource.class).path(Integer.toString(returnLang.getId())).path("/snippet").build().toString();
		returnLang.addLink(uri2, "post snippets");
		String uri3 = uriInfo.getBaseUriBuilder().path(LanguageResource.class).path(Integer.toString(returnLang.getId())).path("/snippet?id=id").build().toString();
		returnLang.addLink(uri3, "put changes to snippet");
		String uri4 = uriInfo.getBaseUriBuilder().path(LanguageResource.class).path("/all").build().toString();
		returnLang.addLink(uri4, "all languages");
		String uri5 = uriInfo.getBaseUriBuilder().path(LanguageResource.class).path("/name?name=" + returnLang.getName()).build().toString();
		returnLang.addLink(uri5, "language by name");
		
	}
	
	
}