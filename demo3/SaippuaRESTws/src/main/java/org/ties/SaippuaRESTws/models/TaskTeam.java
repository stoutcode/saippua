package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskTeam {
	private int taskId;
	private String members;
	
	public TaskTeam() {
		
	}
	
	public TaskTeam(int id, String members) {
		this.taskId = id;
		this.members = members;
	}
	
	public int getId() {
		return this.taskId;
	}
	
	public String getMembers() {
		return this.members;
	}
	
	public void replaceMembers(String names) {
		this.members = names;
	}
	
	public void setId(int id) {
		this.taskId = id;
	}
}
