package com.stockmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Company;
import com.stockmarket.service.RegistrationService;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class RegistrationController {
	@Autowired
	RegistrationService service;

	@PostMapping("/register")
	@ResponseBody
	public Company registerCompany(@RequestBody CompanyHelper company) {
		
		return service.registerCompany(company);
	}
	
	@GetMapping("/info/{id}")
	@ResponseBody
	public Company fetchCompany(@PathVariable String id) {
		return service.fetchCompany(id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public String deleteCompany(@PathVariable String id) {
		String result=null;
		try {
		service.deleteCompany(id);
		result="Company Deleted Successfully";
		}catch (Exception e) {
			result="Something went wrong, Deletion unsuccessful";
		}
		return result;
	}
	
	@GetMapping("/getall")
	@ResponseBody
	public List<Company> fetchAllCompanies() {
		return service.fetchAllCompanies();
	}

}
