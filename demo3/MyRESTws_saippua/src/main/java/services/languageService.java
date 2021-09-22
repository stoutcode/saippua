package services;

import java.util.ArrayList;

import models.Language;

public class languageService {
	private ArrayList<Language> languages = new ArrayList<>();
	
	public static String getAllLanguages() {
		return "{\"languages\": \"languages\"}";
	}
	
	public String getLanguage(long languageId)  {
		return "{\"language\": \"language\"}";
	}
	
	public String addLanguage(String languageString)  {
		return "ok";
	}
}


