
  package com.nic.san.controller;
  
  import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.ResponseEntity;
  import  org.springframework.security.authentication.AuthenticationManager; import
  org.springframework.security.authentication.
  UsernamePasswordAuthenticationToken; import
  org.springframework.security.core.userdetails.UserDetails; import
  org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import
  org.springframework.web.bind.annotation.RestController;
  
  import com.nic.san.config.JwtUtils;
import com.nic.san.model.CustomUserDetail;
import com.nic.san.model.JwtRequest;
  import com.nic.san.model.JwtResponse;
  
  @RestController
  @RequestMapping("/js")
  @CrossOrigin(origins = "http://localhost:4200")
  public class JwtAuthenticationController {
  
  @Autowired 
  UserDetailsService userDetailsservice;
  
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  JwtUtils jwtUtils;
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
  @GetMapping("/cu")
  public CustomUserDetail getCurrentUSer(Principal princlipal)
  {
	  return (CustomUserDetail) userDetailsservice.loadUserByUsername(princlipal.getName());
  }
  private void authenticate(String username,String password) {
	  try {
  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); 
  }catch(Exception e)
  { 
	  e.printStackTrace();
  
  } }
  }
 