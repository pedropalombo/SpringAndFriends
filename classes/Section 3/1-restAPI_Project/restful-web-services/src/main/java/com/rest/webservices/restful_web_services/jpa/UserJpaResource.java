package com.rest.webservices.restful_web_services.jpa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;	// importing all methods from WebMvcLinkBuilder

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.rest.webservices.restful_web_services.user.User;
import com.rest.webservices.restful_web_services.user.UserDaoService;
import com.rest.webservices.restful_web_services.user.UserNotFoundException;

import jakarta.validation.Valid;

// !! this is a copy of 'UserResource', but updated with H2's db utilisation
// controller for REST methods
@RestController
public class UserJpaResource {
	
	private UserDaoService service;	//using DAO to retrieve the info from DB to show on web path
	
	private UserRepository repository;	// switching to H2 persistence instead of DAO
	
	// instancing the values from Resource to use DAO's
	public UserJpaResource(UserDaoService service, UserRepository repository) {
		this.service = service;
		this.repository=repository;
	}
	
	// returning every user from DB and displaying on '/users'
	@GetMapping(path="/jpa/users")
	public List<User> getUsers(){
		//return service.findAll();	// 1st iteration: using DAO methods
		
		return repository.findAll();	// 2nd iteration: using JPA methods
	}
	
	// returning a specific user
	// !! OBS: check on GitHub 'First Steps' commit for previous versions of this
	// applying HATEOAS logic (EntityModel + WebMvcLinkBuilder)
	@GetMapping(path="/jpa/users/{id}")
	public EntityModel<User> getUserById(@PathVariable int id){
		//return service.findUser(id); // --> first iteration
		
		// -- applying Exception Handling logic --
		//User user = service.findUser(id);	// DAO method
		Optional<User> user = repository.findById(id);	// JPA method
		
		// if the user is not found
		if(user.isEmpty()) {
			throw new UserNotFoundException("The following id was not found: "+id);	// throwing a custom error to show the id of the user
		}
		// -- / --
		
		// wrapping the found user in EntityModel's package
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		// adding links to other methods from 'UserResource' with WebMvcLinkBuilder
		// OBS: the methods should be imported first before usage
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getUsers());	// setting a link to '/users' path
		
		// adding said links to the package, together with a name for the respective path
		entityModel.add(link.withRel("all-users"));	
		
		return entityModel;
	}
	
	// creating a user by sending it over to the server
	@PostMapping(path="/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {//getting & validating the user info from the HTTP request (using a Client API tester)
		User savedUser = repository.save(user);
		
		// getting the page (URI) of where the user got sent to upon creation
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();	// attaching the location to the response of the user's creation
	}
	
	// removing the user from the DB
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		//service.deleteUserById(id);	// DAO method
		repository.deleteById(id);	// JPA method
	}

}
