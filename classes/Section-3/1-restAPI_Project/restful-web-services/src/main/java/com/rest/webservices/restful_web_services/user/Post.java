package com.rest.webservices.restful_web_services.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

// creating the posts associated to each user (n->1)
@Entity		// injecting table methods to the class (H2 persistence)
public class Post {
	
	@Id
	@GeneratedValue		// letting the ID of the Post be auto-generated
	private int id;
	
	@Size(max = 200)	// setting the maximum size for the description
	private String description;
	
	// many 'Posts' can be had by one 'User'
	@ManyToOne(fetch = FetchType.LAZY)		// 'Lazy' -> brings forth only the class's (Post) info | 'Eager' -> fetches both classes' info
	@JsonIgnore	// ignoring the list of posts for now
	private User user;	// so we may link the post to the User

	
	// == Auto generated Constructor, getters/setters, and toString() ==
	// -- Post Related --
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	// -- / --
	
	// -- User Related --
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	// -- / --

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
	// == / ==
	

}
