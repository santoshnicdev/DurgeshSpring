package com.nic.san.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nic.san.model.CustomUserDetail;
import com.nic.san.model.User;
import com.nic.san.model.UserRole;
import com.nic.san.repo.RoleRepo;
import com.nic.san.repo.UserRepo;

@Service
public class UserServiceImple implements UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	

	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		User local=userRepo.findByUsername(user.getUsername());
		if(local!=null)
		{
			System.out.println("User Alredy there");
			throw new RuntimeException( "User Alredy Availble");
		}
		else
		{
			     user.setUserRole(userRoles);
			       userRoles.forEach(role->{
			    	   if(roleRepo.findByRolename(role.getRole().getRolename())!=null)
			    	   {
			    		   role.setRole(roleRepo.findByRolename(role.getRole().getRolename()));
			    		   System.out.println("Role Already Exist");
			    		   
			    	   }
			    	   else
			    	   {
			    		   
			    		   roleRepo.save(role.getRole());
			    	   }
			
				
				});
			
			local=userRepo.save(user);
		}
		
	return local;	
		
	
	}

	@Override
	public Optional<User> findUserByID(Long id) {
		      
		     
		Optional<User> user=	userRepo.findById(id);
		if(user.isPresent())
		{
			return user;
		}
		else
		    		                                                             
		throw new RuntimeException("not found");
	}

	@Override
	public String deleteUserByID(Long id) {
		
		if(userRepo.findById(id).isPresent())
		{
			userRepo.deleteById(id);
			return "Delete sucessfully wit id:"+id;
		}
		else
	throw new RuntimeException("not founf");
	}

}
