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
import com.faisal.model.Users;
import com.faisal.repository.UserRepository;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	//POST THE DATA
	@PostMapping("/user")
	Users newUser(@RequestBody Users newUsers) {
		return userRepository.save(newUsers);
	}
	
	
	//GET THE DATA
	@GetMapping("/users")
	List<Users> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	//GET DATA BY ID
	@GetMapping("user/{id}")
	Users getUserById(@PathVariable Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id));
	}
	
	
	//PUT DATA BY ID
	@PutMapping("user/{id}")
	Users updateUser(@RequestBody Users newUsers, @PathVariable Long id) {
		return userRepository.findById(id)
				.map(users -> {
					users.setUsername(newUsers.getUsername());
					users.setName(newUsers.getName());
					users.setEmail(newUsers.getEmail());
					
					return userRepository.save(users);
					
				}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	//DELETE METHOD
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "user with id "+ id+ "has been deleted successfully."; 
	}

}
































