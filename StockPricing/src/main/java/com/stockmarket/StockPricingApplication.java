package com.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StockPricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPricingApplication.class, args);
	}

}
