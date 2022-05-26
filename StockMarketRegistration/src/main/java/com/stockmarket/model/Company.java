package com.stockmarket.model;

import java.io.Serializable;

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
	private String CompanyCode;
	@Column(name="NM_COMPANY")
	private String CompanyName;
	@Column(name="CEO")
	private String CompanyCEO;
	@Column(name="TURNOVER")
	private Integer CompanyTurnover;
	@Column(name="WEBSITE")
	private String CompanyWebsite;

}
