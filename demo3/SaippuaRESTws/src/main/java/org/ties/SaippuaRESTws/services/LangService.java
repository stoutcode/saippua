package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Link;
import org.ties.SaippuaRESTws.models.Snippet;
import org.ties.SaippuaRESTws.models.Language;

public class LangService {
	private List<Language> langs = new ArrayList<>();
	private int id = 0;
	
	private int nextId() {
		return ++this.id;
	}
	
	public LangService() {
		int test = this.langs.size();
		if(test == 0) {
			initialize();
		}
	}
	
	private void initialize() {
		int id = 0;
		nextId();
		
		Language lang = new Language(id, "Java", "Truly a great enterprise language", "Object-oriented language");
		lang.addSnippet("String hello = 'hello world';");
		lang.addSnippet("int id = 0;");
		
		this.langs.add(lang);
		
	}

	public Language getFirstLang() {
		return this.langs.get(0);
	}
	
	public List<Language> getAllLangs() {
		return this.langs;
	}

	public Language getLangById(int id) {
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
		instructions.put("Fields", getFields());
		instructions.put("Links", getLinks());
	
		return instructions;
	}
	
	public List<String> getFields(){
		List<String> fields = new ArrayList<String>();
		
		fields.add("id: identifying id for language");
		fields.add("name: name of language");
		fields.add("description: short description of the language");
		fields.add("type: what kind of programming language is this language");
		fields.add("<snippets>: list of snippets aka. exapmles of how programming language works");
		
		return fields;	
	}

	public List<Link> getLinks() {
		List<Link> links = new ArrayList<Link>();
		
		Link instructions = new Link("/", "instructions");
		links.add(instructions);
		
		Link id = new Link("/id", "search by id as parameter, i.e. /id?1");
		links.add(id);
		
		Link name = new Link("/name", "search by name as parameter, i.e /name?java");
		links.add(name);
		
		Link all = new Link("/all", "get all tasks");
		links.add(all);
		
		Link post = new Link("/", "POST new language as json");
		links.add(post);
		
		Link put = new Link("/{id}", "PUT changes to existing language with same id");
		links.add(put);
		
		Link del = new Link("/{id}/", "Delete language with same id");
		links.add(del);
		
		
		Link snippet = new Link("/languages/snippet/", "POST new snippet relevant to language, i.e /snippet?public String helloWorld() {return \"hello world\"}");
		links.add(snippet);
		
		Link snippetput = new Link("/languages/snippet/", "PUT modify snippet relevant to language, i.e /snippet?public String helloWorld() {return \"hello world\"}");
		links.add(snippetput);
		return links;
	}
	
	public Language addSnippet(Snippet snippet, int id){
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					String snip = snippet.getSnippet();
					lang.addSnippet(snip);					
					returnLang = lang;
				}
			}
		} catch (Exception e) {
			return returnLang;
		}
		
		return returnLang;
	}
	
	public Language updateSnippet(Snippet snippet, int id){
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					String snip = snippet.getSnippet();
					lang.addSnippet(snip);				
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

	public Language removeLanguage(int id) {
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					returnLang = lang;
					langs.remove(lang);
					return returnLang;
				}
			}
		} catch (Exception e) {
			return returnLang;
		}
		
		return returnLang;
	}
}

