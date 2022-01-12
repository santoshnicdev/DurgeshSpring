package com.nic.san.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.san.config.JwtUtils;
import com.nic.san.model.JwtRequest;
import com.nic.san.model.JwtResponse;
import com.nic.san.model.Role;
import com.nic.san.model.User;
import com.nic.san.model.UserRole;
import com.nic.san.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserService userSevice;
	  @Autowired 
	  UserDetailsService userDetailsservice;
	  @Autowired
		BCryptPasswordEncoder  bCryptPasswordEncoder;
	  
	  @Autowired
	  AuthenticationManager authenticationManager;
	  @Autowired
	  JwtUtils jwtUtils;
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/hello")
	public String hellouser()
	{
		return "hello-user";
	}
	@PostMapping("/")
	public User createUser(@RequestBody User user)
	{
		System.out.println("UserController.createUser()"+user.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));;
		Set<UserRole> userRoles=new HashSet<UserRole>();
		
		UserRole userRole2=new UserRole();
		
		
		
    Role role2=new Role();
		
		role2.setRolename("Normal");
		
	   
	    userRole2.setRole(role2);
	    userRole2.setUser(user);
		userRoles.add(userRole2);
		
		return this.userSevice.createUser(user, userRoles);
		
		
	}
	@PostMapping("/gt") 
	  public ResponseEntity<?>  genrateToken(@RequestBody JwtRequest jwtRequest) 
	  {
	  System.out.println("JwtAuthenticationController.genrateToken()");
	  System.out.println("---"+jwtRequest.getUserName()+"00"+jwtRequest.getPassword()); 
	  authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
	  UserDetails userDetails=this.userDetailsservice.loadUserByUsername(jwtRequest.getUserName()); 
	  String token=jwtUtils.generateToken(userDetails); 
	  return ResponseEntity.ok(new JwtResponse(token));
	  
	  }
	  public void authenticate(String username,String password) {
		  try {
	  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 
	  }catch(Exception e)
	  { 
		  e.printStackTrace();
	  
	  } }
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id)
	{
		return userSevice.findUserByID(id);
	}
	@DeleteMapping("/{id}")
	public String removeUser(@PathVariable Long id)
	{
		
		return userSevice.deleteUserByID(id);
		
	}
	

}
