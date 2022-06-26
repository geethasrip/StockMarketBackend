package com.stockmarket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stockmarket.model.Stock;

public interface StockPricingRepository extends JpaRepository<Stock, Integer> {

	@Query(value = "select stockCode,stockPrice from Stock where companyCode=:companyCode ")
	public List<Stock> findByCompanyCode(String companyCode);

	@Query(value = "select stockCode,stockPrice from Stock where companyCode=:companyCode and tmStamp between :startDate and :endDate")
	public List<Stock> fetchStockList(String companyCode, Date startDate, Date endDate);

}
