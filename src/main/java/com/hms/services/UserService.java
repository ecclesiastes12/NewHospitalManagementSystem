package com.hms.services;

import java.util.List;

import com.hms.modules.User;

public interface UserService {

	public List<User> listUsers();
	public User createUser(User user);
	public User updateUser(User user,Long userId);
	public void deleteUser(Long userId);
	public boolean isEmailUnique(Long id, String email);
}
