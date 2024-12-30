package com.hms.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.modules.Role;
import com.hms.modules.RoleRepository;
import com.hms.modules.User;
import com.hms.modules.UserRepository;
import com.hms.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> listUsers() {
		return userRepo.findAll();
	}
	
//	@Override
//	public User createUser(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		
//		return userRepo.save(user);
//	}

	 @Override
	    public User createUser(User user) {
		 // Convert role names (if provided as strings) to Role objects
	        Set<Role> userRoles = new HashSet<>();
	        for (Role role : user.getRoles()) {
	            if (role.getRoleName() != null) {
	                Role existingRole = roleRepo.findByRoleName(role.getRoleName())
	                        .orElseThrow(() -> new IllegalArgumentException("Invalid role name: " + role.getRoleName()));
	                userRoles.add(existingRole);
	            }
	        }
	        user.setRoles(userRoles);
	        return userRepo.save(user);   // Save the user with the associated roles
	    }
	
	@Override
	public User updateUser(User user, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmailUnique(Long id, String email) {
		User getUserEmail = userRepo.findByEmail(email);
		if(getUserEmail == null) return true;
		
		boolean isCreatingNew = id == null;
		
		if(isCreatingNew) {
			// if email is not null meaning the email exist in the db
			// therefore the email is not unique. New email must be provided
			if(getUserEmail != null) {
				return false;
			}
		}else {
			// if the ID of the user find by email is different from the ID
			// of the user being edited then email is not unique
			if(getUserEmail.getId() != id) {
				return false;
			}
						
		}
		
		return true;
		
	}

	

}
