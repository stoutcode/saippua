package org.ties.SaippuaRESTws.models;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Language {
	private int id;
	private String name;
	private String description;
	private String type;
	private List<Snippet> snippets = new ArrayList<>();
	
	public Language() {
		
	}
	
	public Language(int id, String name, String description, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void addSnippet(String snippet) {
		
		snippets.add(new Snippet(snippets.size()+1, this.id, snippet));
	}
	
	public void setSnippet(int id, String newsnippet) {
		for(Snippet snippet : snippets) {
			if(snippet.getID()==id) {
				snippet.setSnippet(newsnippet);
			}
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	
	public List<Snippet> getSnippets(){
		return snippets;
	}

	
}
