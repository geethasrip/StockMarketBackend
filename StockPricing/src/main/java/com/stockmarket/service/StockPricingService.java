package com.stockmarket.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.dto.StockDto;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Stock;

@Service
public class StockPricingService {

	@Autowired
	StockPricingRepository repository;

	public Stock addStock(StockDto stockDto) {
		Stock stock=new Stock();
		BeanUtils.copyProperties(stockDto, stock);
		stock = repository.save(stock);
		return stock;
	}

	public CompanyHelper viewStockDetails(String companyCode, Date startDate, Date endDate) {
		CompanyHelper companyHelper = new CompanyHelper();
		List<Stock> stockList = repository.fetchStockList(companyCode, startDate, endDate);
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
		return companyHelper;
	}

}
