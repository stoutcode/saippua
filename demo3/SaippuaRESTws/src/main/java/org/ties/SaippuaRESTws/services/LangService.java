package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ties.SaippuaRESTws.models.Snippet;
import org.ties.SaippuaRESTws.models.Language;

public class LangService {
	private static List<Language> langs = new ArrayList<>();
	private static int id = 0;
	private static int snipId = 0;
	
	private int nextId() {
		return ++id;
	}
	
	private int nextSnipId() {
		return ++snipId;
	}
	
	public LangService() {
		int test = langs.size();
		if(test == 0) {
			initialize();
		}
	}
	
	private void initialize() {
		
	}

	public Language getFirstLang() {
		return langs.get(0);
	}
	
	public List<Language> getAllLangs() {
		return langs;
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
		
		instructions.put("Info", "This url is for LanguageService.");
		instructions.put("Fields", getFields());
	
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
	
	public Language addSnippet(Snippet snippet, int id){
		Language returnlanguage = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					String snip = snippet.getSnippet();
					lang.addSnippet(nextSnipId(),snip);
					returnlanguage = lang;
					return returnlanguage;
				}
			}
		} catch (Exception e) {
			return returnlanguage;
		}
		
		return returnlanguage;
	}
	
	public Language updateSnippet(Snippet snippet, int snipId, int id){
		Language returnSnippet = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					snippet.setId(snipId);
					snippet.setLangId(id);
					String snip = snippet.getSnippet();
					lang.setSnippet(snipId, snip);
					returnSnippet = lang;
				}
			}
		} catch (Exception e) {
			return returnSnippet;
		}
		
		return returnSnippet;
	}
	
	public Snippet deleteSnippet(int snipId, int id) {
		Snippet returnSnippet = null;
		
		System.out.println("Snippet id: " + snipId + ", Lang id: " + id);

		try {
			for (Language lang : langs) {
				if(lang.getId() == id) {
					
					for (Snippet snip : lang.getSnippets()) {
						if (snip.getID() == snipId) {
							returnSnippet = lang.removeSnippet(snipId);
							return returnSnippet;
						}
					}
				}	
			}
		} catch (Exception e) {
			return returnSnippet;
		}
	
	return returnSnippet;
}

	public Language addLang(Language lang) {
		
		Language returnLang = null;
		
		try {
			String name = lang.getName();
			String description = lang.getDescription();
			String type = lang.getType();
			
			int id = nextId();
			
			Language newLang = new Language(id, name, description, type);
			
			langs.add(newLang);
			
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
					updatedLang.setId(id);
					langs.set(langs.indexOf(lang), updatedLang);
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

