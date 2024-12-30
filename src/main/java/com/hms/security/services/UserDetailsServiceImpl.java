package com.hms.security.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.modules.User;
import com.hms.modules.UserRepository;

/*
 * This class implements UserDetailsService interface that allow us to load user specific data from
 * the custom user module(User entity class) during the authentication process.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private UserRepository userRepo;
	
	public UserDetailsServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}


     
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//retrieve username from the database. NB you can use email if you prefer that instead of username
		User user = userRepo.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return UserDetailsImpl.build(user);
	}

	
	
}
