package com.saurav.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.models.User;
import com.saurav.repository.UserRepository;
import com.saurav.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		
////		User newUser = new User();
////		newUser.setEmail(user.getEmail());
////		newUser.setFirstName(user.getFirstName());
////      newUser.setLastName(user.getLastName());
////      newUser.setPassword(user.getPassword());
////      newUser.setId(user.getId());
////      User savedUser = userRepository.save(newUser);
//      User savedUser = userService.registerUser(user);
//		return savedUser;
//		
//	}

	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User>users = userRepository.findAll();
		return users;
	
		
		
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId" )Integer id ) throws Exception {
	User user = userService.findUserById(id);
	return user;
//	Optional<User> user = userRepository.findById(id);
//	if(user.isPresent()) {
//		return user.get();
//	}
//		throw new Exception("user not exist with userid"+id);
//		return null;
		
	}

	@PutMapping("/api/users")
	public User updateUser(@RequestHeader ("Authorization")String jwt,@RequestBody User user) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updatUser(user,reqUser.getId());
		return updatedUser;
		
		
//	Optional<User> user1 = userRepository.findById(userId);
//	if(user1.isEmpty()) {
//		throw new Exception("user not exist with id"+userId);
//	}
//       User oldUser = user1.get();
//       if(user.getFirstName()!=null) {
//    	   oldUser.setFirstName(user.getFirstName());
//       }
//       if(user.getLastName()!=null) {
//    	   oldUser.setLastName(user.getLastName());
//       }
//       if(user.getEmail()!=null) {
//    	   oldUser.setEmail(user.getEmail());
//       }
//       
//       User updatedUser = userRepository.save(oldUser);
//       return updatedUser;
//		User user1 = new User(1,"saurav","mall","saurav@gmail.com","123456");
//
//		if(user.getFirstName()!=null) {
//		 user1.setFirstName(user.getFirstName());
//		}
//		if(user.getLastName()!=null) {
//			user1.setLastName(user.getLastName());
//			
//		}
//		if(user.getEmail()!=null) {
//			user1.setEmail(user.getEmail());
//			
//		}
//		if(user.getPassword()!=null) {
//			user1.setPassword(user.getPassword());
//			
//		}
//		return user1;
		
	}
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
		
	}
	@GetMapping("/api/users/search")
	public List<User>searchUser(@RequestParam("query")String query){
		List<User>users = userService.searchUser(query);
		return users;
	}
	@DeleteMapping("users/{userId}")
	public String deleteUser(@PathVariable("userId")Integer userId) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new Exception("user not exist with id"+userId);
		}
		userRepository.delete(user.get());
		return "user deleted successfully with id"+userId;
	}
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
//		String email = 
//		System.out.println("jwt--------"+jwt);
		User user = userService.findUserByJwt(jwt);
		String temp = user.getPassword();
		user.setPassword(null);
		return user;
	}
	
}
