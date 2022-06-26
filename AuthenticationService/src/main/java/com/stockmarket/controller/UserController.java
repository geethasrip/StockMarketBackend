package com.stockmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.entity.User;
import com.stockmarket.entity.UserRequest;
import com.stockmarket.entity.UserResponse;
import com.stockmarket.jwtUtil.JwtUtil;
import com.stockmarket.service.IUserService;


@RestController
@RequestMapping("/api/v1.0/market/auth")
public class UserController {
	Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService service;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/registeradmin")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id=service.saveUser(user);
		logger.info("user details saved succesfully");
		return ResponseEntity.ok("User saved with id"+id);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserRequest userRequest)
	{
		if(service.isUserExist(userRequest))
		{
			String token=jwtUtil.generateToken(userRequest.getUsername());
			logger.info("token generated succesully");
			return ResponseEntity.ok(new UserResponse(token,"GENERATED BY STOCKMARKET SERVICE"));
		} else {
			logger.info("error token generating the token");
			return ResponseEntity.ok(new UserResponse("NO TOKEN","USER DOES NOT EXIST!!"));
		}
	}


}