package com.ensat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entities.User;
import com.ensat.repositories.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }	
	
	@Override
	public Iterable<User> listAllUsers() {
        return userRepository.findAll();
	}

	@Override
	public User getUserById(Integer id) {
        return userRepository.findOne(id);
	}

	@Override
	public User saveUser(User user) {
        return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(id);		
	}
	
    @Override
	public User findUserByInfoImpl(String plg, String ppw) {
        return userRepository.findUserByInfo(plg, ppw);
	}	
}
