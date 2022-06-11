package com.stockmarket.helper;

import lombok.Data;

@Data
public class CompanyHelper {
	private String companyCode;
	private String companyName;
	private String companyCEO;
	private Integer companyTurnover;
	private String companyWebsite;
	private Double stockPrice;
	private String startDate;
	private String endDate;
	private Double minStockPrice;
	private Double maxStockPrice;
	private Double averageStockPrice;
}
