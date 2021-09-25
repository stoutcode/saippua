package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Snippet {
	private int id;
	private int languageid;
	private String snippet;
	
	public Snippet() {
		
	}
	
	public Snippet(String snippet) {
		this.snippet = snippet;
	}
	
	public Snippet(int id, int languageid, String snippet) {
		this.id = id;
		this.languageid = languageid;
		this.snippet = snippet;
	}
	
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	
	public String getSnippet() {
		return this.snippet;
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getLangID() {
		return this.languageid;
	}
	
}
