package com.stockmarket.helper;

import java.util.List;

import com.stockmarket.dto.StockDto;

import lombok.Data;

@Data
public class CompanyHelper {
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private Integer companyTurnover;
	private String companyWebsite;
	private List<StockDto> stockList;
	private String stockExchange;
	private Double minStockPrice;
	private Double maxStockPrice;
	private Double averageStockPrice;
}
