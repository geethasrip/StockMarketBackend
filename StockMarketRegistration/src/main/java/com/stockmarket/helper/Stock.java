package com.stockmarket.helper;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data

public class Stock {

	private String companyCode;

	private int stockCode;

	private Double stockPrice;

	private Date tmStamp;

}
