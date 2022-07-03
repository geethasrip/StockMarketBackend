package com.stockmarket.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stockmarket.entity.User;
import com.stockmarket.entity.UserRequest;
import com.stockmarket.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public Integer saveUser(User user) {
		String encodedValue=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedValue);
		return repository.save(user).getId();
	}

	@Transactional
	public User findByUsername(String username) {
		Optional<User> user=repository.findByUsername(username);
		if(user.isPresent()) 
			return user.get();
		return null;
	}

	@Override
	public boolean isUserExist(UserRequest userRequest) {
		User user = findByUsername(userRequest.getUsername());
		if(bCryptPasswordEncoder.matches(userRequest.getPassword(),user.getPassword()))
			return true;
		else 
			return false;
	}
	
}