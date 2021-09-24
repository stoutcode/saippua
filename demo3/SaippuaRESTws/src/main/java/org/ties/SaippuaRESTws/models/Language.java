package org.ties.SaippuaRESTws.models;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Language {
	private int id;
	private String name;
	private String description;
	private String status;
	private String type;
	
	public Language(int id, String name, String description, String status, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
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
	
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getStatus() {
		return this.status;
	}

}
