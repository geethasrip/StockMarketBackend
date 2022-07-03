package com.stockmarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.config.CorsRegistry;

import com.stockmarket.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator configureRoutes(RouteLocatorBuilder builder) {
		
		return builder.routes().route("stockRegId",
				r -> r.path("/api/v1.0/market/stock/**").filters(f -> f.filter(filter)).uri("lb://stock-pricing"))
				.route("authId",
						r -> r.path("/api/v1.0/market/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVICE"))
				.route("companyRegId", r -> r.path("/api/v1.0/market/company/**").filters(f -> f.filter(filter))
						.uri("lb://company-registration"))
				.build();
	}

	
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**").allowedOrigins("http://localhost:3000").allowedMethods("PUT", "DELETE", "POST")
				.allowCredentials(false).maxAge(3600);
	}
	
}
