package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Link;
import org.ties.SaippuaRESTws.models.Language;

public class LangService {
	private List<Language> langs = new ArrayList<>();
	private int id = 0;
	
	private int nextId() {
		return ++this.id;
	}
	
	public LangService() {
		Language lang = new Language(0, "Java", "Truly a great enterprise language", "Object-oriented language");
		this.langs.add(lang);
	}
	
	public Language getFirstLang() {
		return this.langs.get(0);
	}
	
	public List<Language> getAllLangs() {
		return this.langs;
	}

	public Language getLangById(long id) {
		Language returnLang = null;
		
		for (Language lang : langs) {
			if(lang.getId() == id) {
				returnLang = lang;
			}
		}
		
		return returnLang;
	}

	public List<Language> getLangsByNameList(String language) {
		List<Language> returnLangs = new ArrayList<>();
		
		for (Language lang : langs) {
			if(lang.getName().trim().toLowerCase().equals(language.trim().toLowerCase())) {
				returnLangs.add(lang);
			}
		}
		
		return returnLangs;
	}

	public Map<Object, Object> getInstructions() {
		
		Map<Object, Object> instructions = new LinkedHashMap<>();
		
		instructions.put("Info", "This url is for LanguageService. See list of links.");
		instructions.put("Links", getLinks());
		
		return instructions;
	}

	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		
		Link instructions = new Link("/", "instructions");
		links.add(instructions);
		
		Link id = new Link("/languages/id", "search by id as parameter, i.e. /id?1");
		links.add(id);
		
		Link id2 = new Link("/languages/1", "search by id number");
		links.add(id2);
		
		Link name = new Link("/languages/name", "search by name as parameter, i.e /name?java");
		links.add(name);
		
		Link all = new Link("/languages/all", "get all tasks");
		links.add(all);
		
		Link post = new Link("/languages/", "POST new link as json");
		links.add(post);
		
		Link put = new Link("/languages/", "PUT changes to existing link with same id");
		links.add(put);
		
		Link snippet = new Link("/languages/snippet/", "POST new snippet relevant to language, i.e /snippet?public String helloWorld() {return \"hello world\"}");
		links.add(snippet);
		
		Link snippetput = new Link("/languages/snippet/", "PUT modify snippet relevant to language, i.e /snippet?public String helloWorld() {return \"hello world\"}");
		links.add(snippetput);
		return links;
	}
	
	public Language addSnippet(String snippet, int id){
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					lang.addSnippet(snippet);				
					returnLang = lang;
				}
			}
		} catch (Exception e) {
			return returnLang;
		}
		
		
		return returnLang;
	}
	
	public Language updateSnippet(String snippet, int id){
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					lang.addSnippet(snippet);				
					returnLang = lang;
				}
			}
		} catch (Exception e) {
			return returnLang;
		}
		
		
		return returnLang;
	}

	
	public Language addLang(Language lang) {
		
		Language returnLang = null;
		
		try {
			String name = lang.getName();
			String description = lang.getDescription();
			String type = lang.getType();
			
			int id = nextId();
			Language newLang = new Language(id, name, description, type);
			
			this.langs.add(newLang);
			
			returnLang = newLang;
			
		} catch (Exception e) {
			return returnLang;
			
		}
		
		return returnLang;
	}
	
	public Language updateLang(int id, Language updatedLang) {
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					lang = updatedLang;
					returnLang = updatedLang;
				}
			}
		} catch (Exception e) {
			return returnLang;
		}
		
		
		return returnLang;
	}
	
	
}
