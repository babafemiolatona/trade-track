package com.springboot.tradetrack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}

