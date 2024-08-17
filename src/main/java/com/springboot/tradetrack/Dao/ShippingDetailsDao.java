package com.springboot.tradetrack.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.ShippingDetails;

public interface ShippingDetailsDao extends JpaRepository<ShippingDetails, Integer> {

    List<ShippingDetails> findByUserId(Integer userId);

}
