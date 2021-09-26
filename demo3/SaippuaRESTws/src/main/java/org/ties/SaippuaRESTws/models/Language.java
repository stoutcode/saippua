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
	private List<Link> links = new ArrayList<>();
	
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
	
	public Snippet addSnippet(String snippet) {
		int snipID = snippets.size()+1;
		snippets.add(new Snippet(snipID, this.id, snippet));
		
		return getSnippet(snipID);
		
	}
	
	public Snippet setSnippet(int id, String newsnippet) {
		Snippet returnSnip = null;

		for(Snippet snippet : snippets) {
			if(snippet.getID()==id) {
				snippet.setSnippet(newsnippet);
				returnSnip = snippet;
				return returnSnip;
			}
		}
		return returnSnip;
	}
	
	public Snippet removeSnippet(int id2) {
		Snippet returnSnip = null;
		for(Snippet snippet : snippets) {
			if(snippet.getID()==id) {
				returnSnip = snippet;
				snippets.remove(snippet);
				return returnSnip;
			}
		}
		return returnSnip;
	}
	
	public Snippet getSnippet(int id) {
		Snippet returnSnip = null;
		for(Snippet snippet : snippets) {
			if(snippet.getID()==id) {
				returnSnip = snippet;
			}
		}
		return returnSnip;
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

	public void addLink(String href, String rel) {
		Link link = new Link(href, rel);
		this.links.add(link);
	}
	
	public List<Link> getLinks() {
		return this.links;
	}
	
}
