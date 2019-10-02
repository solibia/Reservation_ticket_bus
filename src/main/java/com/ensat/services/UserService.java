package com.ensat.services;


import com.ensat.entities.User;

public interface UserService {
	   Iterable<User> listAllUsers();
	
	   User getUserById(Integer id);
	
	   User saveUser(User user);

	   void deleteUser(Integer id);
	   
	   User findUserByInfoImpl(String plg, String ppw);
}
