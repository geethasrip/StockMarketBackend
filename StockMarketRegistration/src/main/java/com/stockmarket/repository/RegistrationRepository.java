package com.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockmarket.model.Company;


public interface RegistrationRepository extends JpaRepository<Company, String>{

}
