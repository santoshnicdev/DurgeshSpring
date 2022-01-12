package com.nic.san.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nic.san.model.CustomUserDetail;
import com.nic.san.repo.UserRepo;
@Service
public class UserDetailsServiceImple implements UserDetailsService {
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetail user=new CustomUserDetail(userRepo.findByUsername(username)) ;
		System.out.println("UserDetailsServiceImple.loadUserByUsername()"+user);
		//System.out.println("UserDetailsServiceImple.loadUserByUsername()"+user.getAuthorities());
		return user;
	}

}
