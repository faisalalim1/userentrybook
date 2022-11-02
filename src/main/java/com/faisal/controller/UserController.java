package com.faisal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.faisal.exception.UserNotFoundException;
import com.faisal.model.User;
import com.faisal.repository.UserRepository;

@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	//POST THE DATA
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	
	//GET THE DATA
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	//GET DATA BY ID
	@GetMapping("user/{id}")
	User getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id));
	}
	
	
	//PUT DATA BY ID
	@PutMapping("user/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setName(newUser.getName());
					user.setEmail(newUser.getEmail());
					
					return userRepository.save(user);
					
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	//DELET METHOD 
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "user with id "+ id+ "has been deleted successfully."; 
	}

}
































