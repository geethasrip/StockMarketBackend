package com.stockmarket.helper;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CompanyHelper {
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private Long companyTurnover;
	private String companyWebsite;
	private String stockExchange;
	private List<StockDto> stockList;
	private Double stockPrice;
	private Date tmStamp;
}
