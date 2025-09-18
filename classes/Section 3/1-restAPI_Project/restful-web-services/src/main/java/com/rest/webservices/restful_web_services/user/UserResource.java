package com.rest.webservices.restful_web_services.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;	// importing all methods from WebMvcLinkBuilder

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

// controller for REST methods
@RestController
public class UserResource {
	
	private UserDaoService service;	//using DAO to retrieve the info from DB to show on web path
	
	// instancing the values from Resource to use DAO's
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	// returning every user from DB and displaying on '/users'
	@GetMapping(path="/users")
	public List<User> getUsers(){
		return service.findAll();
	}
	
	// returning a specific user
	// !! OBS: check on GitHub 'First Steps' commit for previous versions of this
	// applying HATEOAS logic (EntityModel + WebMvcLinkBuilder)
	@GetMapping(path="/users/{id}")
	public EntityModel<User> getUserById(@PathVariable int id){
		//return service.findUser(id); // --> first iteration
		
		// -- applying Exception Handling logic --
		User user = service.findUser(id);
		
		// if the user is not found
		if(user==null) {
			throw new UserNotFoundException("The following id was not found: "+id);	// throwing a custom error to show the id of the user
		}
		// -- / --
		
		// wrapping the found user in EntityModel's package
		EntityModel<User> entityModel = EntityModel.of(user);
		
		// adding links to other methods from 'UserResource' with WebMvcLinkBuilder
		// OBS: the methods should be imported first before usage
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());	// setting a link to '/users' path
		
		// adding said links to the package, together with a name for the respective path
		entityModel.add(link.withRel("all-users"));	
		
		return entityModel;
	}
	
	// creating a user by sending it over to the server
	@PostMapping(path="/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {//getting & validating the user info from the HTTP request (using a Client API tester)
		User savedUser = service.saveUser(user);
		
		// getting the page (URI) of where the user got sent to upon creation
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();	// attaching the location to the response of the user's creation
	}
	
	// removing the user from the DB
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteUserById(id);
	}
}
