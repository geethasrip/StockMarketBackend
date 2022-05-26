package com.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.model.Company;
import com.stockmarket.repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository repository;

	public Company registerCompany(Company company) {
		Company companyUptated = null;
		Company existingCompany = null;
		if (company.getCompanyTurnover() > 100000000) {
			existingCompany = repository.getById(company.getCompanyCode());
			if (existingCompany != null)
				companyUptated = repository.save(company);
			else
				throw new RuntimeException("Company already exists");
		} else {
			throw new RuntimeException("Company turnover less than 10 crs");
		}

		return companyUptated;

	}

}
