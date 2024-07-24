package com.springboot.tradetrack.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.tradetrack.Models.Order;

public interface OrderDao  extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(Integer userId);

    // Order findById(Integer orderId);

}
