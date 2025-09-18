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

import com.rest.webservices.restful_web_services.user.Post;
import com.rest.webservices.restful_web_services.user.User;
import com.rest.webservices.restful_web_services.user.UserDaoService;
import com.rest.webservices.restful_web_services.user.UserNotFoundException;

import jakarta.validation.Valid;

// !! this is a copy of 'UserResource', but updated with H2's db utilisation
// controller for REST methods
@RestController
public class UserJpaResource {
	
	//private UserDaoService service;	// 1st iteration: using DAO to retrieve the info from DB to show on web path
	
	private UserRepository userRepository;	// 2nd iteration: switching to H2 persistence instead of DAO
	
	private PostRepository postRepository;	// bringing Post's persistence methods
	
	// instancing the values from Resource to use DAO's
	public UserJpaResource(UserDaoService service, UserRepository userRepository, PostRepository postRepository) {
		//this.service = service;
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}
	
	// returning every user from DB and displaying on '/users'
	@GetMapping(path="/jpa/users")
	public List<User> getUsers(){
		//return service.findAll();	// 1st iteration: using DAO methods
		
		return userRepository.findAll();	// 2nd iteration: using JPA methods
	}
	
	// returning a specific user
	// !! OBS: check on GitHub 'First Steps' commit for previous versions of this
	// applying HATEOAS logic (EntityModel + WebMvcLinkBuilder)
	@GetMapping(path="/jpa/users/{id}")
	public EntityModel<User> getUserById(@PathVariable int id){
		//return service.findUser(id); // --> first iteration
		
		// -- applying Exception Handling logic --
		//User user = service.findUser(id);	// DAO method
		Optional<User> user = userRepository.findById(id);	// JPA method
		
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
		User savedUser = userRepository.save(user);
		
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
		userRepository.deleteById(id);	// JPA method
	}

	// returning all posts for the User
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);	// finding the User's info based on ID

		if(user.isEmpty()) {
			throw new UserNotFoundException("The following id was not found: "+id);	// throwing a custom error to show the id of the user
		}
		
		return user.get().getPosts();	// getting the User and their Posts
	}
	
	
	// creating & attributing a Post to a User
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(
			@PathVariable int id, // getting the id of the 'User'
			@Valid @RequestBody Post post // and validating if the request has a 'Post' with the correct structure
	) {
		Optional<User> user = userRepository.findById(id); // finding the User's info based on ID

		if (user.isEmpty()) {
			throw new UserNotFoundException("The following id was not found: " + id); // throwing a custom error to show the id of the user
		}

		post.setUser(user.get()); // setting the owner of the post based on the given request data

		Post savedPost = postRepository.save(post); // saving the post to db as a var so we can envelop with HATEOAS info

		// getting the page (URI) of where the user got sent to upon creation
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();	// attaching the location to the response of the user's creation
	}
	
	// returning a specific Post for a specific User
	@GetMapping(path = "/jpa/users/{userId}/posts/{postId}")
	public EntityModel<Post> getPostFromUser(
			@PathVariable int userId,
			@PathVariable int postId
	) {

		// find both the 'User' and the 'Post', throwing an exception if non-existent
		User user = userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User id not found: " + userId));
		Post post = postRepository.findById(postId)
	            .orElseThrow(() -> new UserNotFoundException("Post id not found: " + postId));

		
		// checking if the 'Post' belongs to given 'User'
		if (!post.getUser().getId().equals(userId)) {
	        throw new UserNotFoundException("Post id " + postId + " does not belong to user id " + userId);		// otherwise, throw an exception
	    }

		// wrapping the found post in EntityModel's package
		EntityModel<Post> entityModel = EntityModel.of(post);

		// adding links to other methods from 'UserJpaResource' with WebMvcLinkBuilder (aka 'all-posts')
		// OBS: the methods should be imported first before usage
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrievePostsForUser(userId)); // setting a link to '[..]/posts' path

		// adding said links to the package, together with a name for the respective path
		entityModel.add(link.withRel("all-posts-from-user"));

		return entityModel;

	}
}
