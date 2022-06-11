package com.stockmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.exception.StockMarketException;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Company;
import com.stockmarket.repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository repository;

	public Company registerCompany(CompanyHelper companyDto) {
		Company companyUptated = null;
		if (companyDto.getCompanyTurnover() > 100000000) {
			Optional<Company> existingCompany = repository.findById(companyDto.getCompanyCode());
			if (existingCompany.isPresent())
				throw new StockMarketException("Company already exists");
			else {
				Company company = new Company();
				BeanUtils.copyProperties(companyDto, company);
				companyUptated = repository.save(company);
			}
		} else {
			throw new StockMarketException("Company turnover less than 10 crs");
		}

		return companyUptated;

	}

	public Company fetchCompany(String id) {
		Optional<Company> company1 = repository.findById(id);
		if (company1.isPresent())
			return company1.get();
		else
			throw new StockMarketException("No Company exists with given Id");
	}

	public void deleteCompany(String id) {
		repository.deleteById(id);
	}

	public List<Company> fetchAllCompanies() {
		return repository.findAll();

	}

}
