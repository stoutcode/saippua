package org.ties.SaippuaRESTws.models;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Principal{
	private String firstName, lastName,
	username, email, password, role;
	private List<Link> links = new ArrayList<Link>();;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName,
		String username, String email,
		String password, String role){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public void addLink(String href, String rel) {
		Link link = new Link(href, rel);
		this.links.add(link);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}; 
	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

}
