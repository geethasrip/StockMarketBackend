package com.stockmarket.service;

import com.stockmarket.entity.User;
import com.stockmarket.entity.UserRequest;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
	public boolean isUserExist(UserRequest userRequest);
}
