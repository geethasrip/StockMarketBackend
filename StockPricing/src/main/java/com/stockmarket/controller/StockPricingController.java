package com.stockmarket.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.dto.StockDto;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Stock;
import com.stockmarket.service.StockPricingService;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(origins = "http://localhost:3000/")
public class StockPricingController {

	@Autowired
	StockPricingService stockPricingService;

	@PostMapping("/add")
	public ResponseEntity<Stock> addStock(@RequestBody StockDto stock) {
		Stock st= stockPricingService.addStock(stock);
		return new ResponseEntity<>(st,HttpStatus.OK);
		 
	}

	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<CompanyHelper> viewStockDetails(@PathVariable String companyCode, @PathVariable Date startDate,
			@PathVariable Date endDate) {
		CompanyHelper com=	stockPricingService.viewStockDetails(companyCode, startDate, endDate);
		return new ResponseEntity<>(com,HttpStatus.OK);
	}

	@GetMapping("/getLatestStockPrice/{companyCode}")
	public ResponseEntity<Double> getLatestStockPrice(@PathVariable String companyCode) {
		Double com=stockPricingService.getLatestStockPrice(companyCode);
		 return new ResponseEntity<>(com,HttpStatus.OK);
	}

	@GetMapping("getStock/{companyCode}")
	public ResponseEntity<List<Stock>> getStockList(@PathVariable String companyCode) {
		 List<Stock> st=stockPricingService.getStockList(companyCode);
		 return new ResponseEntity<>(st,HttpStatus.OK);
	}

	@DeleteMapping("delete/{companyCode}")
	public ResponseEntity<String> deleteStock(@PathVariable String companyCode) {
		String str= stockPricingService.deleteStock(companyCode);
		return new ResponseEntity<>(str,HttpStatus.OK);
	}
	
}
