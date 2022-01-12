package com.nic.san.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.san.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public User findByUsername(String username);

}
