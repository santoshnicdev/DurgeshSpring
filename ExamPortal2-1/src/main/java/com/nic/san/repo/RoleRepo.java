package com.nic.san.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.san.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	public Role findByRolename(String rolename);

}
