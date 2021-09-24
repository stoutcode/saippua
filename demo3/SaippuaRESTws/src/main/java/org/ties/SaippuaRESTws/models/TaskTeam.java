package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskTeam {
	private int taskId;
	private String team;
	
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
	
	public void replaceTeam(String names) {
		this.team = names;
	}
	
	public void setId(int id) {
		this.taskId = id;
	}
}
