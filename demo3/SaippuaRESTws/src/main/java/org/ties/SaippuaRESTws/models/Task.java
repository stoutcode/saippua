package org.ties.SaippuaRESTws.models;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
	private int id;
	private String language;
	private String description;
	private String status;
	private List<Link> links = new ArrayList<>();
	
	public Task() {
		
	}
	
	public Task(int id, String language, String description, String status) {
		this.id = id;
		this.language = language;
		this.description = description;
		this.status = status;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public List<Link> getLinks() {
		return this.links;
	}

}
