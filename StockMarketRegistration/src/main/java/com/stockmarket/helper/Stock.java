package com.stockmarket.helper;

import java.util.Date;

import lombok.Data;

@Data

public class Stock {

	private String companyCode;

	private int stockCode;

	private Double stockPrice;

	private Date tmStamp;

}
