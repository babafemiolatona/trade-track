package com.springboot.tradetrack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.ShippingDetails;

public interface ShippingDetailsDao extends JpaRepository<ShippingDetails, Integer> {

}
