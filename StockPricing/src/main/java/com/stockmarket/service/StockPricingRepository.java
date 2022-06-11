package com.stockmarket.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarket.model.Stock;

public interface StockPricingRepository extends JpaRepository<Stock, Integer> {

	public List<Stock> findByCompanyCode(String companyCode);
	
	public List<Stock> fetchStockList(String companyCode, Date startDate, Date endDate);
	
}
