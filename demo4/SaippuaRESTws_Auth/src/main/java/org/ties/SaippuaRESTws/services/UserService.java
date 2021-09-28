package org.ties.SaippuaRESTws.services;

import java.util.ArrayList;
import java.util.List;

import org.ties.SaippuaRESTws.models.User;

public class UserService {
	private static List<User> users = new ArrayList<>();
	
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
	
}
