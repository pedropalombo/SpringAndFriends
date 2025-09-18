package com.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

// 'User' class that'll be a Bean, so Spring can manage it
@Entity(name="user_details")	// injecting the class with 'H2' db methods, and enveloping it as a table
public class User {
	
	// empty constructor so H2 has a base to start manipulating to/from
	protected User() {
		
	}
	
	@Id	// setting the field as a PK (h2)
	@GeneratedValue	// making it auto-generated
	private Integer id;
	
	// -- Setting validation methods for user creation --
	@Size(min=2, message="Name should have at least 2 characters")	// minimal char input + error message
	@JsonProperty("user_name")	// customising the name of the property using Jackson's methods
	private String name;
	
	@Past(message="The date should be in the past")	// set to only accept dates in the past + error message
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	// -- | --
	
	@OneToMany(mappedBy = "user")		// one 'User' can have many 'Posts' (H2 structure) & linking the value to the Foreign Key in 'Post'
	@JsonIgnore	// ignoring the list of posts for now
	private List<Post> posts;
	
	
	// == Auto generated Constructor, getters/setters, and toString() ==
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	// -- User Related --
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	// -- / --
	
	// -- Post Related --
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	// -- / --

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	// == / ==

	
	
	
}
