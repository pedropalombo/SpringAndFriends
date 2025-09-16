package com.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

// class to manage the DB operations regarding the 'User' class
// PS: DAO -> Data Access Object
@Component	// pointing out that this class is Spring-managed
public class UserDaoService {
	
	//setting the DB using JPA/Hibernate (future)
	
	//setting the DB using an array list (present)
	private static List<User> users = new ArrayList<>();
	
	//setting the value to be used for users' ids
	private static int usersIndex = 0;
	
	//setting instances of 'Users' to be used
	static {
		users.add(new User(++usersIndex, "Giorgio", LocalDate.now().minusYears(24)));
		users.add(new User(++usersIndex, "Magnus", LocalDate.now().minusYears(48)));
		users.add(new User(++usersIndex, "Celestia", LocalDate.now().minusYears(900)));
	}
	
	// returning all users
	public List<User> findAll(){
		return users;
	}
	
	// saving a user to the current DB (list)
	public User saveUser(User user) {
		user.setId(++usersIndex);
		users.add(user);
		
		return user;
	}
	
	// fetching a user by their ID
	public User findUser(int id) {
		
		for (User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		
		//return new User(0,"Nothing was found",LocalDate.now()); // lousy error handling (but it's for the lols)

		return null;		
	}
	
	// removing a user based on ID
	public void deleteUserById(int id) {
		User foundUser = this.findUser(id);
		
		if(foundUser != null) {
			users.remove(foundUser);
		}	
	}

}
