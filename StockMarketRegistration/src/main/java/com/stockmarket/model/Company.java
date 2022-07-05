package com.stockmarket.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Company implements Serializable{
	
	private static final long serialVersionUID = -3764503867770589072L;
	
	@Id
	@Column(name="CD_COMPANY")
	private String companyCode;
	@Column(name="NM_COMPANY")
	private String companyName;
	@Column(name="CEO")
	private String companyCEO;
	@Column(name="TURNOVER")
	private Integer companyTurnover;
	@Column(name="WEBSITE")
	private String companyWebsite;
	@Column(name="STOCK_EXCHANGE")
	private String stockExchange;
	@Column(name="TM_STAMP")
	private Date tmStamp;

}
