package com.springboot.tradetrack.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Order;

public interface OrderDao  extends JpaRepository<Order, Integer> {

}
