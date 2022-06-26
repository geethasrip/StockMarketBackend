package com.stockmarket.service;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.stockmarket.dto.StockDto;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Stock;
import com.stockmarket.repository.StockPricingRepository;

@Service
public class StockPricingService {
	
	@Autowired
	DiscoveryClient client;

	@Autowired
	StockPricingRepository stockPricingRepository;

	public Stock addStock(StockDto stockDto) {
		Stock stock = new Stock();
		BeanUtils.copyProperties(stockDto, stock);
		stock = stockPricingRepository.save(stock);
		return stock;
	}

	public CompanyHelper viewStockDetails(String companyCode, Date startDate, Date endDate) {
		List<ServiceInstance> instances=client.getInstances("company-registration");
		ServiceInstance instance=instances.get(0);
		URI companyServiceUri= instance.getUri();
		CompanyHelper companyHelper = new CompanyHelper();
		List<Stock> stockList = stockPricingRepository.fetchStockList(companyCode, startDate, endDate);
		if (!stockList.isEmpty()) {
			Double min = stockList.get(0).getStockPrice();
			Double max = stockList.get(0).getStockPrice();
			Double sum = 0.0;
			for (Stock stock : stockList) {
				if (stock.getStockPrice() < min) {
					min = stock.getStockPrice();
				} else if (stock.getStockPrice() > max) {
					max = stock.getStockPrice();
				}
				sum = sum + stock.getStockPrice();
			}
			Double average;
			if (stockList.isEmpty())
				average = 0.0;
			else
				average = sum / stockList.size();
			companyHelper.setAverageStockPrice(average);
			companyHelper.setMaxStockPrice(max);
			companyHelper.setMinStockPrice(min);

		}
		return companyHelper;
	}

}
