package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.List;

import org.ties.SaippuaRESTws.models.Language;

public class LangService {
	private List<Language> langs = new ArrayList<>();
	private int id = 0;
	
	private int nextId() {
		return ++this.id;
	}
	
	public LangService() {
		Language lang = new Language(0, "Java", "Truly a great enterprise language", "Sadly still breathing", "Object-oriented language");
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
	
	public Language addLang(Language lang) {
		
		Language returnLang = null;
		
		try {
			String name = lang.getName();
			String description = lang.getDescription();
			String status = lang.getStatus();
			String type = lang.getType();
			
			int id = nextId();
			Language newLang = new Language(id, name, description, status, type);
			
			this.langs.add(newLang);
			
			returnLang = newLang;
			
		} catch (Exception e) {
			return returnLang;
			
		}
		
		return returnLang;
	}
	
	public Language updateLang(Language updatedLang) {
		Language returnLang = null;
		
		try {
			for (Language lang : langs) {
				if(lang.getId() == updatedLang.getId()) {
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
