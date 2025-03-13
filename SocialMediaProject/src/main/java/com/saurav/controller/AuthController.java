package com.saurav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saurav.config.JwtProvider;
import com.saurav.models.User;
import com.saurav.repository.UserRepository;
import com.saurav.request.LoginRequest;
import com.saurav.response.AuthResponse;
import com.saurav.service.CustomUserDetailService;
import com.saurav.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomUserDetailService customUserDetails;
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
  User isExist = userRepository.findByEmail(user.getEmail());
  if(isExist!=null) {
	  throw new Exception("email already used with another account");
  }
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setPassword((passwordEncoder.encode(user.getPassword()))); 
//      User savedUser = userRepository.save(newUser);
      User savedUser = userService.registerUser(newUser);
      Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser,savedUser);
      String token = JwtProvider.generateToken(authentication);
      AuthResponse res = new AuthResponse(token,"Register Succesfull");
		return res;
			
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		 String token = JwtProvider.generateToken(authentication);
	      AuthResponse res = new AuthResponse(token,"Login Success");
			return res;
	}
	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		if(userDetails == null) {
			throw new BadCredentialsException ("invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw  new BadCredentialsException("password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//	      Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser,savedUser);

		
	}
}
