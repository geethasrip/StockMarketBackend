package com.stockmarket.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StockDto {
	private String companyCode;
	private Integer stockCode;
	private Double stockPrice;
	private Date tmStamp;
}
