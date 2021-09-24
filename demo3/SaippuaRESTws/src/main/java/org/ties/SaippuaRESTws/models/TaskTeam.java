package org.ties.SaippuaRESTws.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskTeam {
	private int taskId;
	private String team;
	private List<Link> links = new ArrayList<>();
	
	public TaskTeam() {
		
	}
	
	public TaskTeam(int id, String members) {
		this.taskId = id;
		this.team = members;
	}
	
	public int getId() {
		return this.taskId;
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public void setTeam(String names) {
		this.team = names;
	}
	
	public void setId(int id) {
		this.taskId = id;
	}
	
	public void addLink(Link link) {
		this.links.add(link);
	}
	
	public List<Link> getLinks() {
		return this.links;
	}
}
