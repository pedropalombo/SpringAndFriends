package com.rest.webservices.restful_web_services.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

// 'User' class that'll be a Bean, so Spring can manage it
public class User {
	private Integer id;
	
	// -- Setting validation methods for user creation --
	@Size(min=2, message="Name should have at least 2 characters")	// minimal char input + error message
	@JsonProperty("user_name")	// customising the name of the property using Jackson's methods
	private String name;
	
	@Past(message="The date should be in the past")	// set to only accept dates in the past + error message
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	// -- | --
	
	
	// == Auto generated Constructor, getters/setters, and toString() ==
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	// == / ==

	
	
	
}
