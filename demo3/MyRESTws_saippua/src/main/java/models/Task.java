package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
	private long id;
	private String language;
	private String description;
	private String status;
	
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
	
	public long getId() {
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

}
