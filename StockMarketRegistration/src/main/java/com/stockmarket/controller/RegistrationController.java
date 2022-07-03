package com.stockmarket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.exception.StockMarketException;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Company;
import com.stockmarket.service.RegistrationService;

@RestController
@RequestMapping("/api/v1.0/market/company")
@CrossOrigin(origins = "http://localhost:3000/")
public class RegistrationController {
	Logger logger=LoggerFactory.getLogger(RegistrationController.class);
	@Autowired
	RegistrationService service;

	@PostMapping("/register")
	public Company registerCompany(@RequestBody CompanyHelper company) {
		
		return service.registerCompany(company);
	}
	
	@GetMapping("/info/{id}")
	public ResponseEntity<?> fetchCompany(@PathVariable String id) {
		try {
		CompanyHelper company=service.fetchCompany(id);
		return new ResponseEntity<>(company,HttpStatus.OK);
		}catch (StockMarketException e) {
			return new ResponseEntity<>(e,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCompany(@PathVariable String id) {
		String result=null;
		try {
		service.deleteCompany(id);
		result="Company Deleted Successfully";
		}catch (Exception e) {
			result="Something went wrong, Deletion unsuccessful";
			logger.error(e.getMessage());
		}
		return result;
	}
	
	@GetMapping("/getall")
	@ResponseBody
	public List<CompanyHelper> fetchAllCompanies() {
		return service.fetchAllCompanies();
	}
	
}
