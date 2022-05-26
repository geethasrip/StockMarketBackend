package com.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.model.Company;
import com.stockmarket.service.RegistrationService;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class RegistrationController {
	@Autowired
	RegistrationService service;

	@PostMapping("/register")
	@ResponseBody
	public Company registerCompany(@RequestBody Company company) {
		Company result=service.registerCompany(company);
		return result;
	}
	
	
	
	

}
