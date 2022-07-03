package com.stockmarket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.stockmarket.dto.StockDto;
import com.stockmarket.model.Stock;

public interface StockPricingRepository extends JpaRepository<Stock, Integer> {

	@Query(value = "select s from Stock s where companyCode=:companyCode ")
	public List<Stock> findByCompanyCode(String companyCode);

	@Query(value = "select s from Stock s where companyCode=:companyCode and tmStamp between :startDate and :endDate")
	public List<Stock> fetchStockList(String companyCode, Date startDate, Date endDate);
	
	@Modifying
	@Query(value="delete from Stock where companyCode=:companyCode")
	public void deleteByCompanyCode(String companyCode);
	


}
