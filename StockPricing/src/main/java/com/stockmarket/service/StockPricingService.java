package com.stockmarket.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stockmarket.dto.StockDto;
import com.stockmarket.exception.StockMarketException;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Stock;
import com.stockmarket.repository.StockPricingRepository;

@Service
public class StockPricingService {
	private final Logger logger = LoggerFactory.getLogger(StockPricingService.class);
	@Autowired
	DiscoveryClient client;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	StockPricingRepository stockPricingRepository;

	public Stock addStock(StockDto stockDto) {
		stockDto.setStockCode(1);
		stockDto.setTmStamp(new Date());
		Stock stock = new Stock();
		BeanUtils.copyProperties(stockDto, stock, "stockCode");
		stock = stockPricingRepository.save(stock);
		return stock;
	}

	public CompanyHelper viewStockDetails(String companyCode, Date startDate, Date endDate) {

		List<ServiceInstance> instances = client.getInstances("company-registration");
		ServiceInstance instance = instances.get(0);
		URI companyServiceUri = instance.getUri();
		logger.info("===inside view Stocks====");
		CompanyHelper companyHelper = null;
		StockDto stockDto = new StockDto();
		List<StockDto> stList = new ArrayList<>();
		ResponseEntity<?> response = null;
		response = restTemplate.getForEntity(companyServiceUri + "/api/v1.0/market/company/info/" + companyCode,
				CompanyHelper.class);
		companyHelper = (CompanyHelper) response.getBody();
		if (companyHelper != null) {
			logger.info("Company details {}", companyHelper);
			List<Stock> stockList = stockPricingRepository.fetchStockList(companyCode, startDate, endDate);
			if (!stockList.isEmpty()) {
				Double min = stockList.get(0).getStockPrice();
				Double max = stockList.get(0).getStockPrice();
				Double sum = 0.0;
				for (Stock stock : stockList) {
					stockDto.setCompanyCode(stock.getCompanyCode());
					stockDto.setStockCode(stock.getStockCode());
					stockDto.setStockPrice(stock.getStockPrice());
					stockDto.setTmStamp(stock.getTmStamp());
					if (stock.getStockPrice() < min) {
						min = stock.getStockPrice();
					} else if (stock.getStockPrice() > max) {
						max = stock.getStockPrice();
					}
					sum = sum + stock.getStockPrice();
					stList.add(stockDto);
				}
				Double average;
				average = sum / stockList.size();

				companyHelper.setAverageStockPrice(average);
				companyHelper.setMaxStockPrice(max);
				companyHelper.setMinStockPrice(min);
				companyHelper.setStockList(stList);
			}
			return companyHelper;
		} else {
			throw new StockMarketException("Company does not exist");
		}

	}

	public Double getLatestStockPrice(String companyCode) {
		Double latestStockPrice = 0.0;
		Date date = new Date(1800, 01, 01);
		List<Stock> list = stockPricingRepository.findByCompanyCode(companyCode);
		if (!list.isEmpty()) {
			for (Stock stock : list) {
				if (stock.getTmStamp().before(date)) {
					latestStockPrice = stock.getStockPrice();

				}
			}
		}
		return latestStockPrice;
	}

	public List<Stock> getStockList(String companyCode) {
		return  stockPricingRepository.findByCompanyCode(companyCode);
	}

	@Transactional
	public String deleteStock(String companyCode) {
		logger.info("======= Inside Delete Stock====");
		stockPricingRepository.deleteByCompanyCode(companyCode);
		logger.info("====Exiting deleteStock ====");
		return "deleted Successfully";
	}

}
