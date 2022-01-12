package com.nic.san.service;

import java.util.Optional;
import java.util.Set;

import com.nic.san.model.CustomUserDetail;
import com.nic.san.model.User;
import com.nic.san.model.UserRole;

public interface UserService {
	public User createUser(User user,Set<UserRole> userRoles);
	public Optional<User>  findUserByID(Long id);
	public String deleteUserByID(Long id);

}
