package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ties.SaippuaRESTws.models.User;

public class UserService {
	private static List<User> users = new ArrayList<>();
	private static List<String> roles = Arrays.asList("admin", "manager", "worker");
	
	public UserService() {
		
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public User getUserByUsername(String username) {
		User returnUser = null;
		for (User user : users) {
			if (user.getUsername() == username) {
				returnUser = user;
			}
		}
		return returnUser;
	}
	
	public User addUser(User user) {
		if ( !isValidRole(user.getRole()) ) {
			user.setRole("worker");
		}
		
		if (user.getUsername() == null) {
			return null;
		} else {
			user.setUsername(user.getUsername().trim().toLowerCase());
		}
		
		if ( isUniqueUsername(user.getUsername()) ) {
			users.add(user);
			return user;
		} else {
			return null;
		}
	}
	
	private boolean isUniqueUsername(String username) {
		if (username == null || username.trim().equals("") ) {
			return false;
		}
		for (User user : users) {
			if ( user.getUsername().equals(username) ) {
				return false;
			}
		}
		return true;
	}

	public User deleteUser(String username) {
		User returnUser = null;
		for (User user : users) {
			if ( user.getUsername().equals(username) ) {
				users.remove(user);
				returnUser = user;
			}
		}
		return returnUser;
	}
	
	public User setRole(String username, String role) {
		User returnUser = null;
		
		for (User user : users) {
			if ( user.getUsername().equals(username) ) {
				user.setRole(role);
				returnUser = user;
			}
		}
		return returnUser;
	}

	public boolean isValidRole(String role) {
		if (role == null || role.trim() == "" || !roles.contains(role)) {
			return false;
		} else {
			return true;
		}
	}

	public String getRoles() {
		return roles.toString();
	}

	public String getUsernames() {
		StringBuilder usernames = new StringBuilder();
		for (User user : users) {
			usernames.append(" " + user.getUsername() + ",");
		}
		return usernames.toString();
	}
	
}
