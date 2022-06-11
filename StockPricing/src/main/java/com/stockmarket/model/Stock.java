package com.stockmarket.model;

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
@Entity
public class Stock {
	@Column(name = "CD_COMPANY")
	private String companyCode;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer stockCode;
	@Column(name = "STOCK_PRICE")
	private Double stockPrice;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date tmStamp;

}
