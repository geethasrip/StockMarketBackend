package com.stockmarket.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Stock addStock(@RequestBody StockDto stock) {
		return stockPricingService.addStock(stock);
	}

	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public CompanyHelper viewStockDetails(@PathVariable String companyCode, @PathVariable Date startDate,
			@PathVariable Date endDate) {
		return stockPricingService.viewStockDetails(companyCode, startDate, endDate);
	}

	@GetMapping("/getLatestStockPrice/{companyCode}")
	public Double getLatestStockPrice(@PathVariable String companyCode) {
		return stockPricingService.getLatestStockPrice(companyCode);
	}

	@GetMapping("getStock/{companyCode}")
	public List<Stock> getStockList(@PathVariable String companyCode) {
		return stockPricingService.getStockList(companyCode);
	}

	@DeleteMapping("delete/{companyCode}")
	public String deleteStock(@PathVariable String companyCode) {
		return stockPricingService.deleteStock(companyCode);
	}
	
}
