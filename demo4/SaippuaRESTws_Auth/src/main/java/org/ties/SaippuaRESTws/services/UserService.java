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
		users.add(user);
		return user;
	}
	
	public User changeUser(User newUser) {
		User returnUser = null;
		for (User user : users) {
			if ( user.getUsername().equals(newUser.getUsername()) ) {
				users.set(users.indexOf(user), newUser);
				returnUser = newUser;
			}
		}
		return returnUser;
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
	
}
