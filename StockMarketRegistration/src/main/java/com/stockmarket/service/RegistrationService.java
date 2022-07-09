package com.stockmarket.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stockmarket.constants.RegistrationContants;
import com.stockmarket.exception.StockMarketException;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.helper.StockDto;
import com.stockmarket.model.Company;
import com.stockmarket.repository.RegistrationRepository;

@Service
public class RegistrationService {
	@Autowired
	RegistrationRepository repository;

	@Autowired
	DiscoveryClient client;
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${stockpricinginstance}")
	 String stockPricingURl;

	public Company registerCompany(CompanyHelper companyDto) {
		companyDto.setTmStamp(new Date());
		Company companyUptated = null;
		if (companyDto.getCompanyTurnover() > 100000000) {
			Optional<Company> existingCompany = repository.findById(companyDto.getCompanyCode());
			if (existingCompany.isPresent())
				throw new StockMarketException(RegistrationContants.ALREADYEXISTS);
			else {
				Company company = new Company();
				BeanUtils.copyProperties(companyDto, company);
				companyUptated = repository.save(company);
			}
		} else {
			throw new StockMarketException(RegistrationContants.TURNOVER_ERROR);
		}

		return companyUptated;

	}

	public CompanyHelper fetchCompany(String id) {
		CompanyHelper company = new CompanyHelper();
		Optional<Company> company1 = repository.findById(id);
		if (company1.isPresent()) {
			List<ServiceInstance> instances = client.getInstances(stockPricingURl);
			ServiceInstance instance = instances.get(0);
			URI companyServiceUri = instance.getUri();
			ResponseEntity<?> response = null;
			response = restTemplate.getForEntity(companyServiceUri + "/api/v1.0/market/stock/getLatestStockPrice/" + id,
					Double.class);
			Double latestStockPrice = (Double) response.getBody();

			BeanUtils.copyProperties(company1.get(), company);

			company.setStockPrice(latestStockPrice);
			return company;
		} else
			throw new StockMarketException(RegistrationContants.COMPANYDOESNOTEXIST);
	}


	public void deleteCompany(String id) {
		List<ServiceInstance> instances = client.getInstances(stockPricingURl);
		ServiceInstance instance = instances.get(0);
		URI companyServiceUri = instance.getUri();
		restTemplate.delete(companyServiceUri+"/api/v1.0/market/stock/delete/"+id);
		repository.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	public List<CompanyHelper> fetchAllCompanies() {
		List<Company> companyList = repository.findAll();
		List<ServiceInstance> instances = client.getInstances(stockPricingURl);
		ServiceInstance instance = instances.get(0);
		URI companyServiceUri = instance.getUri();
		List<CompanyHelper> companyStockList = new ArrayList<>();

		for (Company company : companyList) {
			CompanyHelper companyHelper = new CompanyHelper();
			BeanUtils.copyProperties(company, companyHelper);
			List<StockDto> stockList =  restTemplate.getForObject(
					companyServiceUri + "/api/v1.0/market/stock/getStock/" + company.getCompanyCode(), List.class);
			Double latestStockPrice = restTemplate.getForObject(companyServiceUri + "/api/v1.0/market/stock/getLatestStockPrice/" + company.getCompanyCode(),
					Double.class);
			companyHelper.setStockPrice(latestStockPrice);
			companyHelper.setStockList(stockList);
			companyStockList.add(companyHelper);
		}
		return companyStockList;

	}

}
