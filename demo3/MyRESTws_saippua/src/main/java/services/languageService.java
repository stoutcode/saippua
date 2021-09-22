package services;

import java.util.ArrayList;

import models.Language;

public class languageService {
	private ArrayList<Language> languages = new ArrayList<>();
	
	public static String getAllLanguages() {
		return "{\"languages\": \"languages\"}";
	}
	
	public static String getLanguage(long languageId)  {
		return "{\"language\": \"language\"}";
	}
	
	public static String addLanguage(Language language)  {
		return "ok";
	}
}


