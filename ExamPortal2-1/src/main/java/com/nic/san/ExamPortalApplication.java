package com.nic.san;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nic.san.service.UserService;

@SpringBootApplication
public class ExamPortalApplication  {


	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception { User user=new
	 * User(); user.setUsername("user1122"); user.setEmail("a@b.com");
	 * user.setFirstname("santosh"); user.setLastname("chouhan");
	 * user.setEnabled(true); user.setPassword("password"); user.setPhone(888);
	 * user.setProfiles("high"); Set<UserRole> userRoles=new HashSet<UserRole>();
	 * UserRole userRole1=new UserRole(); Role role1=new Role();
	 * //role1.setRoleid(44); role1.setRolename("Admin"); userRole1.setRole(role1);
	 * userRole1.setUser(user); userRoles.add(userRole1);
	 * System.out.println("ExamPortalApplication.run()"+userRole1.getRole());
	 * 
	 * 
	 * // user.setUserRole(userRoles);
	 * 
	 * User user1= this.uservice.createUser(user, userRoles);
	 * 
	 * }
	 */

}
