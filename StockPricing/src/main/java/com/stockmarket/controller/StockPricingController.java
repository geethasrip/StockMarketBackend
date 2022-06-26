package com.stockmarket.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockmarket.dto.StockDto;
import com.stockmarket.helper.CompanyHelper;
import com.stockmarket.model.Stock;
import com.stockmarket.service.StockPricingService;

@Controller
@RequestMapping("/api/v1.0/market/stock")
public class StockPricingController {

	@Autowired
	StockPricingService stockPricingService;

	@PostMapping("/add")
	@ResponseBody
	public Stock addStock(@RequestBody StockDto stock) {
		return stockPricingService.addStock(stock);
	}

	@GetMapping("/get/{companyCode}/{startDate}/{endDate}")
	public CompanyHelper viewStockDetails(@PathVariable String companyCode, @PathVariable Date startDate,
			@PathVariable Date endDate) {
		return stockPricingService.viewStockDetails(companyCode, startDate, endDate);
	}

}
